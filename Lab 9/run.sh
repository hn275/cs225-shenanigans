javac ./PairSum.java

echo "\n****Pair 10****"
java PairSum ./pair_10.txt

echo "\n****Pair 10,000****"
java PairSum ./pair_100000.txt

echo "\n****Pair 1,000,000****"
java PairSum ./pair_1000000.txt

echo "\n****No Pair 10****"
java PairSum ./no_pair_10.txt

echo "\n****No Pair 10,000****"
java PairSum ./no_pair_100000.txt

echo "\n****No Pair 1,000,000****"
java PairSum ./no_pair_1000000.txt


rm PairSum*.class
