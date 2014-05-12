<?php
include('simple_html_dom.php');

// get DOM from URL or file
$html = file_get_html('http://www.goremountain.com/mountain/alpine-conditions.cfm');

// get lifts
foreach($html->find('div[class=mountain-section-lift-container]') as $liftdiv) {
    //lift name
    $lift['name'] = str_replace("\t", "", $liftdiv-> plaintext);
    
    // lift open?
    if (strpos($liftdiv, ' open') != false) {
        $lift['open'] = true;
    }
    else {
        $lift['open'] = false;
    }
    
    $liftslist[] = $lift;
}

$fulllist['lifts'] = $liftslist;

// get trails
foreach($html->find('div[class=trail-conditions-mountain-section]') as $sectiondiv) {
	// get section name to later assign. this happens to be the first div inside the section div
    $titlediv = $sectiondiv->find('div',0);

    $currentsectionname = $titlediv->plaintext;
    
    //get info for each trail in that section and store it
    foreach($sectiondiv -> find('div[class=mountain-section-trail]') as $traildiv){
        //$traildifficultydiv = $traildiv->find('div[.difficulty]')->outertext;
        
		//trail name
		$trail['name'] = $traildiv-> plaintext;
        
        
		// trail open?
        if (strpos($traildiv, ' open') != false) {
            $trail['open'] = true;    
        }
        else {
            $trail['open'] = false;
        }
        
        
		// get trail difficulty
		if (strpos($traildiv,'easier') != false)
		{
			$trail['difficulty'] = 'easier';
		}
		else if(strpos($traildiv,'more-difficult') != false)
		{
			$trail['difficulty'] = 'more-difficult';
		}
             else if(strpos($traildiv,'most-difficult') != false)
		{
			$trail['difficulty'] = 'most-difficult';
		}
		else 
		{
			$trail['difficulty'] = 'dblblack';
		}
		
		// groomed?
		if (strpos($traildiv,'groomed') != false)
		{
			$trail['groomed'] = true;
		}
		else
		{
			$trail['groomed'] = false;
		}
		
		// area
		$trail['area'] = $currentsectionname;
		
		// add trail to list
		$trailslist[] = $trail;
    }
}
$fulllist['trails'] = $trailslist;
$json = json_encode($fulllist);

echo $json;
return $json;
?>

