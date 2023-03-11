for f in ./output/*; do
    out=$(cat $f)
    echo "${f}: [${out}]"
done



