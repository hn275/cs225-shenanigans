echo "COMPILING"
javac ./ArrayMatch.java && echo "DONE"

for f in ./input/*; do
    start=$(date +%s.%N)
    out=$(java ArrayMatch $f)
    dur=$(echo "$(date +%s.%N) - $start" | bc)
    echo "${out}, [${dur}]"
done

# java ArrayMatch ./input/input00.txt
