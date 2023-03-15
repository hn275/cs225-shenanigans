package main

import (
	"fmt"
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

	solutions := []string{}
	for _, v := range outputPaths {
		cmd, err := exec.Command("cat", v).Output()
		if err != nil {
			panic(err)
		}

		s := strings.TrimSpace(string(cmd))
		solutions = append(solutions, s)
	}

	inputResult := []string{}
	failed := []string{}
	for i, v := range inputPaths {
		fmt.Printf("Running %s\n", v)
		inputResult = append(inputResult, run(v))
		result := run(v)
		if result != solutions[i] {
			fmt.Printf("\t[FAILED] %s, expected: %s, got: %s\n", v, solutions[i], result)
			failed = append(failed, v)
		} else {
			if i != len(inputPaths)-1 {
				fmt.Print("\033[G\033[K\033[A") // move cursor up left
			} else {
				fmt.Println()
			}

		}
	}

	if len(failed) == 0 {
		log.Printf("[SUCCESS] ran %d test cases.\n", len(inputResult))
	} else {
		log.Printf("[FAILED] %d case(s):", len(failed))
		for _, v := range failed {
			fmt.Printf("\t%s\n", v)
		}
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
