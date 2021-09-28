# -- Path setup --------------------------------------------------------------

# import os
# import sys
# sys.path.insert(0, os.path.abspath('.'))


# -- Project information -----------------------------------------------------

project = "Temi d'esame svolti"
copyright = '2021'
author = ''

# -- MyST configuration ---------------------------------------------------

myst_enable_extensions = [
  'colon_fence',
]

# -- General configuration ---------------------------------------------------

extensions = [
  'myst_parser',
]
templates_path = ['_templates']
language = 'it'
exclude_patterns = []

# -- Options for HTML output -------------------------------------------------

html_theme = 'sphinx_book_theme'
html_static_path = ['_static']
html_theme_options = {
  'single_page': False,
  'logo_only': True,
  'toc_title': 'Sezioni',
  'show_prev_next': False,
  'extra_navbar': '',
  'show_navbar_depth': 2
}
html_logo = '_static/logo.png'
html_show_copyright = False
html_last_updated_fmt = '%-d/%-m/%y'

# -- Options for LaTeX output -------------------------------------------------

latex_logo = '_static/logo.png'
