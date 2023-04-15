run() {
    for file in ./TestCases/input/*; do
        java WayFinder $file >> run.txt
    done
}

test() {
    DIFF=$(diff output.txt run.txt)
    if [[  -z "${DIFF}" ]]; then
        echo "Pass"
    else
        echo "Failed:"
        echo $DIFF
    fi
}


# javac WayFinder.java && java WayFinder ./TestCases/input/input00.txt && rm ./WayFinder.class
javac WayFinder.java && run && test && rm ./*.class run.txt

