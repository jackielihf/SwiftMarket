
# script to build DataSimulator java program

javac ../src/sim/*.java -d .
jar -cvmf ../MANIFEST.MF dataSimulator.jar sim/*.class
