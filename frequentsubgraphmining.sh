#!/bin/bash
cd /home/sam/Documents/gaston-1.1/gaston-1.1    
    
chmod a+x *   #Gives everyone execute permissions

FILES=/home/sam/Documents/gaston-1.1/gaston-1.1/FSM_GRAPHSFILTERED/*

for f in $FILES
do
echo " Processing $f file"
./gaston 70 -t  $f  
done 
#./gaston 70 -t  $ipfilname $opfilname
 