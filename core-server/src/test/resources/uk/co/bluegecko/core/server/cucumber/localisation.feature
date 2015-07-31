Feature: Localisation, single test

Scenario: Request localised version of a string
  Given Localised colours have been defined for "en-GB"
  When Request localised version of "blue"
  Then The response should be "Blue"
