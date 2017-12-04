from string import Formatter
#Functions for the 3 different Index Schemas

def year_document(year, document_number):
    zeroed_document = "0" * (20 - len(document_number)) + document_number
    return '{}-{}.tif'.format(year, zeroed_document)

def year_book_page(year, book_number, page_number):
    zeroed_book = "0" * (10 - len(book_number)) + book_number
    zeroed_page = "0" * (10 - len(page_number)) + page_number
    return '{}-{}-{}.tif'.format(year, zeroed_book, zeroed_page)

def year_document_book_page(year, document_number, book_number, page_number):
    zeroed_document = "0" * (20 - len(document_number)) + document_number
    zeroed_book = "0" * (10 - len(book_number)) + book_number
    zeroed_page = "0" * (10 - len(page_number)) + page_number
    return '{}-{}-{}-{}.tif'.format(year, zeroed_document, zeroed_book, zeroed_page)

def rename_multi_page_tif(img, new_name):
    img.save(new_name, save_all=True)


