Feature: Localisation, multiple tests

Scenario Outline: Request localised version of strings
  Given Localised colours have been defined for "<locale>"
  When Request localised version of "<key>"
  Then The response should be "<value>"

Examples:
  |locale|key  |value|
  |en    |red  |Red  |
  |en    |blue |Blue |
  |en    |green|Green|
  |fr    |red  |Rouge|
  |fr    |blue |Bleu |
  |fr    |green|Vert |

