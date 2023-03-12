package main

import (
	"io/fs"
	"log"
	"os"
	"os/exec"
	"regexp"
	"strings"
)

var files fs.FS

func main() {
	files = os.DirFS(".")
	inputPaths := []string{}
	outputPaths := []string{}

	fs.WalkDir(files, ".", func(path string, d fs.DirEntry, err error) error {
		if err != nil {
			return err
		}

		inputPathRegex := regexp.MustCompile(`input\d+\.txt`)
		if inputPathRegex.Match([]byte(d.Name())) {
			inputPaths = append(inputPaths, path)
		}

		outputPathRegex := regexp.MustCompile(`output\d+\.txt`)
		if outputPathRegex.Match([]byte(d.Name())) {
			outputPaths = append(outputPaths, path)
		}

		return nil
	})

	inputResult := []string{}
	for _, v := range inputPaths {
		inputResult = append(inputResult, run(v))
	}

	solutions := []string{}
	for _, v := range outputPaths {
		cmd, err := exec.Command("cat", v).Output()
		if err != nil {
			panic(err)
		}

		s := strings.TrimSpace(string(cmd))
		solutions = append(solutions, s)
	}

	if len(solutions) != len(inputResult) {
		panic("[ERROR] solutions and results don't have the same length.")
	}

	passedAll := true
	for i, v := range inputPaths {
		if solutions[i] == inputResult[i] {
			continue
		}

		log.Printf("[FAILED] case: %s, expected: %s, got: %s\n", v, solutions[i], inputResult[i])
		passedAll = false
	}

	if passedAll {
		log.Printf("[SUCCESS] ran %d test cases.\n", len(inputResult))
	}

	cleanup()
}

func cleanup() {
	reg := regexp.MustCompile(`\.class$`)
	fs.WalkDir(files, ".", func(path string, d fs.DirEntry, err error) error {
		f := d.Name()
		if reg.Match([]byte(f)) {
			if err := exec.Command("rm", f).Run(); err != nil {
				panic(err)
			}
		}

		return nil
	})

}

func run(inputFile string) string {
	cmd := exec.Command("javac", "./ArrayMatch.java")
	if err := cmd.Run(); err != nil {
		log.Fatal(err)
	}
	cmd.Wait()

	o, err := exec.Command("java", "ArrayMatch", inputFile).Output()
	if err != nil {
		panic(err)
	}

	result := string(o)
	result = strings.Split(result, "\n")[1]
	return result
}
