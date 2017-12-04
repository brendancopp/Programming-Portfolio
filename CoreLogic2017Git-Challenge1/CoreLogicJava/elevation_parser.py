import sys          #For command line args
import csv          #CSV Reader
import srtm         #Elevation module

#CSV RESOURCE LINK: http://www.pythonforbeginners.com/systems-programming/using-the-csv-module-in-python/


        #***CSVObj and CSVObj builder method***
#Contains a row,col, and elevation for reading/writing
class CSVObject(object):
    rownum = 0
    lat = 0
    long = 0
    elevation = 0

    #Class "constructor"
    def __init__(self, rownum):
        self.rownum = rownum

    #To string method
    def __str__(self):
        toS = "Row: " + str(self.rownum) + "\nLat: " + str(self.lat) + "\nLong: " + str(self.long) + "\nElevation: " + str(self.elevation)
        return toS

def make_CSVObj(rownum):
    dataObj = CSVObject(rownum)
    return dataObj

#***********************************
#*********** MAIN METHOD ***********
#***********************************
def mainCSV(readPath, writePath, latHeader, longHeader):

    #Reads csv and returns a list of objects
    CSVObjList = readCSV(readPath, latHeader, longHeader)
    #print(len(CSVObjList))

    #Modifies CSVObjects with elevation data
    getElevation(CSVObjList)

    #Writes Elevation back to CSV
    writeCSV(writePath, CSVObjList)


#***CSV reader and Writer method***
def readCSV(readPath, latHeader, longHeader):
    CSVObjList = [] #Blank obj list

    #Open file logic
    try:
        file = open(readPath, 'rb') # opens the csv file

        #Read CSV into list
        try:
            reader = csv.DictReader(file);  # creates the reader object

            hasCreatedObj = False
            rownum = 0
            for row in reader:
                if row[latHeader] != 'NULL' and row[latHeader] != 'NULL':
                    CSVObj = make_CSVObj(rownum)
                    CSVObj.lat = row[latHeader]
                    CSVObj.long = row[longHeader]

                    CSVObjList.append(CSVObj)
                rownum += 1
        finally:
            file.close()               # Closes File

    except IOError:
        print("Error opening: ", readPath)
        prgError()

    return CSVObjList


def writeCSV(writePath, CSVObjList):
    #Open file logic

    with open(writePath, 'wb') as file:
        #Read CSV into list
        try:

            my_dict = {"ELEVATION": 1}
            elevationWriter = csv.DictWriter(file, {"ELEVATION": 1})  # creates the reader object
            elevationWriter.writeheader()   #Writes header to file

            #Dictionaty Writing logic
            rownum = 0
            listSize = len(CSVObjList)
            while rownum < listSize:
                if CSVObjList[rownum].elevation != 0:
                    my_dict = {"ELEVATION": CSVObjList[rownum].elevation} #lat
                    elevationWriter.writerow(my_dict)
                else:
                    my_dict = {"ELEVATION": 'NULL'} #Write NULL Value
                    elevationWriter.writerow(my_dict)

                rownum += 1
        except IOError as e:
            print "I/O error({0}): {1}".format(e.errno, e.strerror)
            prgError()
        finally:
            file.close()               # Closes File

    return CSVObjList

#Modifies list with elevation data
def getElevation(CSVObjList):
    elevation_data = srtm.get_data()

    for CSVObj in CSVObjList:
        #print(CSVObj.lat, CSVObj.long)
        CSVObj.elevation = elevation_data.get_elevation(CSVObj.lat, CSVObj.long)


def prgError():
    print("This module reads a csv's lats and longs and writes elevation to a CSV file")
    print("Program Instruction: [Program Name] [Read File] [Write File] [Latitude Header] [Longitude Header] ")
    sys.exit(1)

def headerNotFoundException(header):
    print("Header: ", header, ", not found")


        #***Main module code***
if len(sys.argv) < 5:   #Make sure command line contains read/write file and 2 header names
    prgError()

        #Read file,   write file, lat header, long header
mainCSV(sys.argv[1], sys.argv[2], sys.argv[3], sys.argv[4])
print(sys.argv[2])





