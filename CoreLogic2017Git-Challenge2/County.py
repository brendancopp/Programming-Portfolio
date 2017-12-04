from os import listdir
import IndexSchemas
from os.path import join, isfile, isdir
from os import mkdir
from PIL import Image, ImageFile
from WordProcessor import valid_dates, valid_id
from PIL import Image
from BoxSearch import get_text_from_image
import CropBox


def run_county(county_name, extraArgs):
    #Get county name in directory form
    county = county_name.__name__.upper().replace('_', ' ') + '\\'
    output_path = extraArgs[1] + county

    #Check to see if county output directory exists
    if not(isdir(output_path)):
        mkdir(output_path)

    #Make county directory
    county_dir = extraArgs[0] + county

    onlyfiles = [f for f in listdir(county_dir) if (isfile(join(county_dir, f))) and f != 'Thumbs.db']

    print(onlyfiles)
    for file in onlyfiles:
        county_name(extraArgs[0] + county + file, extraArgs[1] + county)

def ca_los_angeles(file_name, output_dir):
    # Store pixel locations of interest points
    document_location = CropBox.Box(909, 223, 1415, 303)
    year_location = CropBox.Box(987, 513, 1121, 557)

    #Open file
    img = Image.open(file_name)

    # Extract text from locations
    document = get_text_from_image(img, document_location)
    date = get_text_from_image(img, year_location)

    # Format new name
    year = "20" + date[6:8]
    new_file_name = IndexSchemas.year_document(year, document)

    # Save new image
    IndexSchemas.rename_multi_page_tif(img, output_dir + new_file_name)
    img.close()

    #Print data for debugging
    print(document)
    print(year)

def in_miami(file_name, output_dir):
    # Store pixel locations of interest points
    #                       (L,U,R,D)
    info_location = (1651, 34, 2483, 408)

    #Open file
    img = Image.open(file_name)

    # Extract text from locations
    document = get_text_from_image(img, info_location)


    # Process Data
    try:
        dateObj = valid_dates(document)
        dateStr = str(dateObj.year)
    except Exception:
        dateStr = "DATE-ERROR"

    document_id = valid_id(document)
    if document_id == None:
        document_id = "DOCUMENT-ERROR"


    zeroed_document_id = "0" * (20 - len(document_id)) + document_id
    new_file_name = dateStr + "-" + zeroed_document_id + ".tif"

    # Save new image
    img.save(output_dir + new_file_name, save_all=True)
    img.close()
    print(zeroed_document_id)
    print(dateStr)

