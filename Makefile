SRC= ./src/datasetgenerator/*.java
OUT= ./bin/

.phony: clean run

all: $(SRC)
	javac $(SRC) -d $(OUT)

run:
	cd bin; java datasetgenerator.DatasetGenerator

clean:
	rm -rf $(OUT)*
