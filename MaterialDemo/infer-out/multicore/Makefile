INFERANALYZE= /Users/global/Documents/infer/infer-osx-v0.1.0/infer/infer/bin/InferAnalyze $(INFER_OPTIONS) -results_dir '/Users/global/Documents/Kohls_Material_Demo_app/MaterialDemo/infer-out'

OBJECTS=cl1 cl2 cl3 cl4 cl5 cl6 cl7 cl8 cl9 cl10 cl11 cl12 cl13 

default: test

all: test

test: $(OBJECTS)
	echo "Analysis done"

cl1 : 
	$(INFERANALYZE) -cluster xcl1.cluster >cl1

cl2 : 
	$(INFERANALYZE) -cluster xcl2.cluster >cl2

cl3 : 
	$(INFERANALYZE) -cluster xcl3.cluster >cl3

cl4 : 
	$(INFERANALYZE) -cluster xcl4.cluster >cl4

cl5 : cl2
	$(INFERANALYZE) -cluster xcl5.cluster >cl5

cl6 : 
	$(INFERANALYZE) -cluster xcl6.cluster >cl6

cl7 : 
	$(INFERANALYZE) -cluster xcl7.cluster >cl7

cl8 : 
	$(INFERANALYZE) -cluster xcl8.cluster >cl8

cl9 : cl8
	$(INFERANALYZE) -cluster xcl9.cluster >cl9

cl10 : cl3 cl5 cl8
	$(INFERANALYZE) -cluster xcl10.cluster >cl10

cl11 : cl5 cl7 cl8
	$(INFERANALYZE) -cluster xcl11.cluster >cl11

cl12 : cl3 cl6 cl7 cl9 cl11
	$(INFERANALYZE) -cluster xcl12.cluster >cl12

cl13 : cl6 cl10 cl12
	$(INFERANALYZE) -cluster xcl13.cluster >cl13


clean:
	rm -f $(OBJECTS)
