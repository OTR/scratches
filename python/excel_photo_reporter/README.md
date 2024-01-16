## This is a test project to compose an Excel report (.xlsx) with a lot of pictures using python

### How to install:

#### via Pip:

    $ python -m venv excel_venv
    $ cd excel_venv
    (Linux) $ source bin/activate
    (Windows) $ Scripts\activate.bat
    $ git clone https://github.com/OTR/Excel-Photo-Reporter.git excel_report
    $ cd excel_report
    $ pip install -r requirements.txt
    $ python main.py

#### via Pipenv:

    $ pip install pipenv
    $ git clone https://github.com/OTR/Excel-Photo-Reporter.git excel_report
    $ cd excel_report
    $ pipenv install
    $ pipenv shell
    $ python main.py


### Run tests:

Run all tests:

`$ python -m unittest`

To run exact TestCase:

`$ python -m unittest tests.test_2nd_create_amazon_books_report.TestCreateAmazonBooksReport
`

### Run linter

`$ flake8 --append-config linter-setup.cfg`

### TODO List:

* ~~catch `PermissionError: [Errno 13] Permission denied: 'workbook.xlsx'` when trying to open already opened file~~ Done
* 

### Project Structure

* tree command for Windows: `$ cmd //c tree //a //f excel/`

#### Tree output

      |   linter-setup.cfg
      |   main.py
      +---scripts
      |   |   scrape_books_from_amazon.py
      \---tests
          |   test_1st_create_workbook.py
          |   test_2nd_create_amazon_books_report.py
          |   test_3rd_create_fixed_excel_spreadsheet.py
          |   test_scrape_books.py
          +---input
          |   |   amazon.html
          |   |   handmade_books_from_amazon.xlsx
          |   +---2nd_test_case
          |   |       handmade_amazon_books_fields.csv
          |   +---3rd_test_case
          |   |       data.xlsx
          |   |       parcel.jpg
          |   +---book_covers
          +---output
          |   +---1st_test_case
          |   |       1st_test_create_workbook.xlsx
          |   +---2nd_test_case
          |   |       2nd_test_create_amazon_book_report.xlsx
          |   +---3rd_test_case
          |   |   |   fixed_data.xlsx
          |   |   \---images
          |   \---scrape_books


`linter-setup.cfg` custom rules for `flake8`

#### `/tests/` folder contains test cases for unittest

#### `/tests/output/` folder to keep automatically created spreadsheets

#### `/tests/input/` folder to keep test data

#### `/scripts/` folder to keep one use scripts for gathering test data

### Test Cases:

#### 1st Test Case:

Create a simple spreadsheet
  
* fill it up with some numbers
  
* save it on a disk

* read it from a disk

* make sure numbers are right

#### 2nd Test Case:

Create a report about existing books on amazon by given keyword (by default keyword is `python`)

1. Gather a dataset about books from amazon using `urllib` and `lxml`

2. Save it on a disk as CSV file (separator is a semicolon)

3. Load it from a disk in memory

4. Create and fill up a spreadsheet with that dataset

5. Widen each row up to 165 pt (in Excel measures) except the 1st row (row with titles) which is 57 pt

6. Widen rows according to the following (in Excel measures):

    * Position:                  5 pt
    * Title:                    36 pt
    * Author(s):                11 pt
    * Price for paperback, $:   11 pt
    * Date of publishing:       11 pt
    * Cover:                    21 pt

7. Change the 1st row:
    * Background color to light blue RGB(155, 194, 230)
    * Horizontally align text to center
    * Vertically align text to top
    * Make a font style bold

8. Change other rows:
    * Horizontally align text to left
    * Vertically align text to top

9. Change background color of the "Cover" column to gray RGB(123, 123, 123)

10. Make a thin black border around each cell

11. Insert an image into each cell in "Cover" column:
    * Make it's padding to 10% of cell width
    * Make an image being auto-resized with a cell
  
12. Make a worksheet protected from modifying

13. Make page borders up to 0,5 cm and get rid of running titles (header, footer)

### 3rd Test Case:

1. Read given broken Excel spreadsheet, get values of cells

2. Create a new spreadsheet with name `fixed_file.xlsx` with columns as:
   * First column (A) is the number of row in given spreadsheet
   * Second column (B) is the address of a shelf's cell on a stock
   * Third column (C) is the broken barcode of a good in shelf's cell
   * Fourth column (D) is the fixed barcode of the good
   

3.