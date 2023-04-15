run() {
    for file in ./TestCases/input/*; do
        java WayFinder $file
        echo "--"
    done
}


# javac WayFinder.java && java WayFinder ./TestCases/input/input00.txt && rm ./WayFinder.class
javac WayFinder.java && run && rm ./*.class
