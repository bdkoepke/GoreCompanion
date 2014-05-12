<?php
include('simple_html_dom.php');

// get DOM from URL or file
$html = file_get_html('http://www.goremountain.com/mountain/alpine-conditions.cfm');


$numOfTrails = $html -> find('span[class=alpineTrailNumber]',0) -> innertext;
$numOfLifts = $html ->find('span[class=alpineLiftNumber]',0) -> plaintext;
$baseDepth = $html -> find('div[class=alpineConditionsContainerRight]',0)-> find('span',0)  -> plaintext;
$primarySurface =  $html -> find('div[class=alpineConditionsContainerRight]',0) -> find('span',1) -> plaintext;
$secondarySurface =  $html -> find('div[class=alpineConditionsContainerRight]',0) -> find('span',2) -> plaintext;

$initArray['Trails'] = $numOfTrails;
$initArray['Lifts'] = $numOfLifts;
$initArray['Base'] = $baseDepth;
$initArray['Primary'] = $primarySurface;
$initArray['Secondary'] = $secondarySurface;

//$mainArray['ConditionSummary'] = $initArray;
$json = json_encode($initArray);

echo $json;

?>