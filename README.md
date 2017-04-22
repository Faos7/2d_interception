# Checking whether 2 2d primitives(square/triangle/circle) intercept

## How to run:
1. Change directory to src/
2. Compile Main.java

## For example: 
1. `cd src/main/`
2. `javac Main.java`
3. `java Main`

program asks you to choose 2d primitive and then enter it parameters
(e.g. center and radius for circle, 2 diagonal points for square, 3points for triangle);

program output - all interception points + overlapping segmants (
some segments may be in reverse, like "overlapping from (1,0) to (-1,0)
if 2 segments are in the same line and have 1 common point - program prints overlapping in that point)
