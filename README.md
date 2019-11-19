# Dataset from Distribution

For this project, you generate a dataset of values according to a specified
distribution.

Specifically, this program generates a textfile *test.output*. Each line
of this text file is a value between 1 and 20 (inclusive bounds). The values
are sampled according to the following table:

| Value | Count          |
|:-----:|:--------------:|
| 20    | 5              |
| 19    | 10             |
| 18    | 25             |
| 17    | 50             |
| 16    | 100            |
| 15    | 250            |
| 14    | 500            |
| 13    | 1000           |
| 12    | 83,000         |
| 11    | 83,000         |
| 10    | 83,000         |
|  9    | 83,000         |
|  8    | 83,000         |
|  7    | 83,000         |
|  6    | 83,000         |
|  5    | 83,000         |
|  4    | 83,000         |
|  3    | 83,000         |
|  2    | 83,000         |
|  1    | 83,000         |

Rules for the dataset:
1. All 997940 values must be in it (one line per value).
2. No two consecutive values in the dataset should be the same.

This program will also print out all lines that contain the value 20.

## How to run this application
First make sure you have both the latest version of JDK and JRE installed.
There is a Makefile in place so you can use that to compile and run the application.

In your favorite shell, navigate to the root of the project.

To build the application run this command:
`make all`

To run the application run this command:
`make run`

### What if I can't run makefiles?
No worries! Those are mostly for my sake. As long as you are in the root of the project,
you should be able to run the following commands.

To build:
`javac ./src/datasetgenerator/*.java -d ./bin/`

To run, first navigate to the bin folder:
`cd bin`

Then run:
`java datasetgenerator.DatasetGenerator`

