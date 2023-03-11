echo "COMPILING"
javac ./ArrayMatch.java && echo "DONE"

for f in ./input/*; do
    out=$(java ArrayMatch $f)
    echo "${f} [${out}]"
done

# java ArrayMatch ./input/input00.txt
