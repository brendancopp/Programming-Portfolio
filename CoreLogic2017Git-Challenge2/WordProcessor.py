import re
import datetime

def valid_dates(dateStr):
    match = re.search(r"\d{1,2}/\d{1,2}/\d{4}", dateStr)
    try:
        date = datetime.datetime.strptime(match.group(0), "%m/%d/%Y")
        return date
    except Exception:
        return None


def valid_id(str):
    match = re.search(r"[0-9]{11}", str)
    if match == None:
        return None
    else:
        return match.group(0)


    # try:
    #
    #     print "DATE " + date
    #     return date
    #     # or you can yield match.group(0) if you just want to
    #     # yield the date as the string it was found like 05-04-1999
    # except ValueError:
    #     # date couldn't be parsed by datetime... invalid date
    #     pass
    # print "LAST "

# def yield_valid_dates(dateStr):
#     for match in re.finditer(r"\d{1,2}/\d{1,2}/\d{4}", dateStr):
#         try:
#             date = datetime.datetime.strptime(match.group(0), "%m-%d-%Y")
#             print "DATE " + date
#             yield date
#             # or you can yield match.group(0) if you just want to
#             # yield the date as the string it was found like 05-04-1999
#         except ValueError:
#             # date couldn't be parsed by datetime... invalid date
#             pass
#     print "LAST " + date






