echo "COMPILING"
javac ./ArrayMatch.java && echo "DONE"

for f in ./input/*; do
    out=$(java ArrayMatch $f)
    echo "${out}"
done

# java ArrayMatch ./input/input00.txt
