<?php

include('simple_html_dom.php');

// get DOM from URL or file
$html = file_get_html('http://www.goremountain.com/mountain/alpine-conditions.cfm');


$maindiv = $html->find('div.intContentContainer',0)->innertext;

$conditionsTableText = $html->find('table',10)->innertext;
$conditionsTable = $html->find('table',8);

$northwoodsTable = $conditionsTable->find('table',1);
$northSideTable = $conditionsTable->find('table',3);
$straightBrookTable = $conditionsTable->find('table',5);
$highPeaksTable = $conditionsTable->find('table',7);
$topridgeTable = $conditionsTable->find('table',9);
$burntRidgeTable = $conditionsTable->find('table',11);
$gladesTable = $conditionsTable->find('table',13);
$skiBowlTable = $conditionsTable->find('table',15);



// Gets north woods info
foreach($northwoodsTable->find('tr') as $trailnode) {
	if (strpos($trailnode->outertext,'bgcolor="#000000"') === false
		&& strpos($trailnode->outertext,'alt="easier"') === false
		&& strpos($trailnode->outertext,'alt="more difficult"') === false
		&& strpos($trailnode->outertext,'alt="most difficult"') === false)
	{

		// name checker
		$trail['name'] = $trailnode->plaintext;
		// groomed checker
		if (strpos($trailnode->find('td',0)->outertext,'img') != false)
			$trail['groomed'] = 'true';
		else 
			$trail['groomed'] = 'false';
			
		// open checker	
		if (strpos($trailnode->find('td',1)->outertext,'img') != false)
			$trail['open'] = 'true';
		else 
			$trail['open'] = 'false';
		
		// difficulty checker	
		if (strpos($trailnode->find('td',2)->outertext,'beginner') != false)
			$trail['diff'] = 'green';
		else if  (strpos($trailnode->find('td',2)->outertext,'intText') != false)
			$trail['diff'] = 'blue';
		else if (strpos($trailnode->find('td',2)->outertext,'expert') != false)
			$trail['diff'] = 'black';
		else 
			$trail['diff'] = 'dblblack';
		
		$trail['area'] = 'Northwoods';

		$trailArray[] = $trail;
	}
}

// Gets north side info
foreach($northSideTable->find('tr') as $trailnode) {
	if (strpos($trailnode->outertext,'bgcolor="#000000"') === false
		&& strpos($trailnode->outertext,'alt="easier"') === false
		&& strpos($trailnode->outertext,'alt="more difficult"') === false
		&& strpos($trailnode->outertext,'alt="most difficult"') === false)
	{

		// name checker
		$trail['name'] = $trailnode->plaintext;
		// groomed checker
		if (strpos($trailnode->find('td',0)->outertext,'img') != false)
			$trail['groomed'] = 'true';
		else 
			$trail['groomed'] = 'false';
			
		// open checker	
		if (strpos($trailnode->find('td',1)->outertext,'img') != false)
			$trail['open'] = 'true';
		else 
			$trail['open'] = 'false';
		
		// difficulty checker	
		if (strpos($trailnode->find('td',2)->outertext,'beginner') != false)
			$trail['diff'] = 'green';
		else if  (strpos($trailnode->find('td',2)->outertext,'intText') != false)
			$trail['diff'] = 'blue';
		else if (strpos($trailnode->find('td',2)->outertext,'expert') != false)
			$trail['diff'] = 'black';
		else 
			$trail['diff'] = 'dblblack';
		
		$trail['area'] = 'NorthSide';

		$trailArray[] = $trail;
	}
}

// Gets straight brook info
foreach($straightBrookTable->find('tr') as $trailnode) {
	if (strpos($trailnode->outertext,'bgcolor="#000000"') === false
		&& strpos($trailnode->outertext,'alt="easier"') === false
		&& strpos($trailnode->outertext,'alt="more difficult"') === false
		&& strpos($trailnode->outertext,'alt="most difficult"') === false)
	{

		// name checker
		$trail['name'] = $trailnode->plaintext;
		// groomed checker
		if (strpos($trailnode->find('td',0)->outertext,'img') != false)
			$trail['groomed'] = 'true';
		else 
			$trail['groomed'] = 'false';
			
		// open checker	
		if (strpos($trailnode->find('td',1)->outertext,'img') != false)
			$trail['open'] = 'true';
		else 
			$trail['open'] = 'false';
		
		// difficulty checker	
		if (strpos($trailnode->find('td',2)->outertext,'beginner') != false)
			$trail['diff'] = 'green';
		else if  (strpos($trailnode->find('td',2)->outertext,'intText') != false)
			$trail['diff'] = 'blue';
		else if (strpos($trailnode->find('td',2)->outertext,'expert') != false)
			$trail['diff'] = 'black';
		else 
			$trail['diff'] = 'dblblack';
		
		$trail['area'] = 'StraightBrook';

		$trailArray[] = $trail;
	}
}

// Gets high peaks info
foreach($highPeaksTable->find('tr') as $trailnode) {
	if (strpos($trailnode->outertext,'bgcolor="#000000"') === false
		&& strpos($trailnode->outertext,'alt="easier"') === false
		&& strpos($trailnode->outertext,'alt="more difficult"') === false
		&& strpos($trailnode->outertext,'alt="most difficult"') === false)
	{

		// name checker
		$trail['name'] = $trailnode->plaintext;
		// groomed checker
		if (strpos($trailnode->find('td',0)->outertext,'img') != false)
			$trail['groomed'] = 'true';
		else 
			$trail['groomed'] = 'false';
			
		// open checker	
		if (strpos($trailnode->find('td',1)->outertext,'img') != false)
			$trail['open'] = 'true';
		else 
			$trail['open'] = 'false';
		
		// difficulty checker	
		if (strpos($trailnode->find('td',2)->outertext,'beginner') != false)
			$trail['diff'] = 'green';
		else if  (strpos($trailnode->find('td',2)->outertext,'intText') != false)
			$trail['diff'] = 'blue';
		else if (strpos($trailnode->find('td',2)->outertext,'expert') != false)
			$trail['diff'] = 'black';
		else 
			$trail['diff'] = 'dblblack';
		
		$trail['area'] = 'High Peaks';

		$trailArray[] = $trail;
	}
}

// Gets topridge info
foreach($topridgeTable->find('tr') as $trailnode) {
	if (strpos($trailnode->outertext,'bgcolor="#000000"') === false
		&& strpos($trailnode->outertext,'alt="easier"') === false
		&& strpos($trailnode->outertext,'alt="more difficult"') === false
		&& strpos($trailnode->outertext,'alt="most difficult"') === false)
	{

		// name checker
		$trail['name'] = $trailnode->plaintext;
		// groomed checker
		if (strpos($trailnode->find('td',0)->outertext,'img') != false)
			$trail['groomed'] = 'true';
		else 
			$trail['groomed'] = 'false';
			
		// open checker	
		if (strpos($trailnode->find('td',1)->outertext,'img') != false)
			$trail['open'] = 'true';
		else 
			$trail['open'] = 'false';
		
		// difficulty checker	
		if (strpos($trailnode->find('td',2)->outertext,'beginner') != false)
			$trail['diff'] = 'green';
		else if  (strpos($trailnode->find('td',2)->outertext,'intText') != false)
			$trail['diff'] = 'blue';
		else if (strpos($trailnode->find('td',2)->outertext,'expert') != false)
			$trail['diff'] = 'black';
		else 
			$trail['diff'] = 'dblblack';
		
		$trail['area'] = 'TopRidge';

		$trailArray[] = $trail;
	}
}

// Gets burnt ridge info
foreach($burntRidgeTable->find('tr') as $trailnode) {
	if (strpos($trailnode->outertext,'bgcolor="#000000"') === false
		&& strpos($trailnode->outertext,'alt="easier"') === false
		&& strpos($trailnode->outertext,'alt="more difficult"') === false
		&& strpos($trailnode->outertext,'alt="most difficult"') === false)
	{

		// name checker
		$trail['name'] = $trailnode->plaintext;
		// groomed checker
		if (strpos($trailnode->find('td',0)->outertext,'img') != false)
			$trail['groomed'] = 'true';
		else 
			$trail['groomed'] = 'false';
			
		// open checker	
		if (strpos($trailnode->find('td',1)->outertext,'img') != false)
			$trail['open'] = 'true';
		else 
			$trail['open'] = 'false';
		
		// difficulty checker	
		if (strpos($trailnode->find('td',2)->outertext,'beginner') != false)
			$trail['diff'] = 'green';
		else if  (strpos($trailnode->find('td',2)->outertext,'intText') != false)
			$trail['diff'] = 'blue';
		else if (strpos($trailnode->find('td',2)->outertext,'expert') != false)
			$trail['diff'] = 'black';
		else 
			$trail['diff'] = 'dblblack';
		
		$trail['area'] = 'BurntRidge';

		$trailArray[] = $trail;
	}
}

	
// Gets glades info
foreach($gladesTable->find('tr') as $trailnode) {
	if (strpos($trailnode->outertext,'bgcolor="#000000"') === false
		&& strpos($trailnode->outertext,'alt="easier"') === false
		&& strpos($trailnode->outertext,'alt="more difficult"') === false
        && strpos($trailnode->outertext,'alt="intermediate trail"') === false
		&& strpos($trailnode->outertext,'alt="most difficult"') === false)
	{
    
		// name checker
		$trail['name'] = $trailnode->plaintext;
		// groomed checker
		if (strpos($trailnode->find('td',0)->outertext,'img') != false)
			$trail['groomed'] = 'true';
		else 
			$trail['groomed'] = 'false';
        
		// open checker	
		if (strpos($trailnode->find('td',1)->outertext,'img') != false)
			$trail['open'] = 'true';
		else 
			$trail['open'] = 'false';
		
		// difficulty checker	
		if (strpos($trailnode->find('td',2)->outertext,'beginner') != false)
			$trail['diff'] = 'green';
		else if  (strpos($trailnode->find('td',2)->outertext,'intText') != false)
			$trail['diff'] = 'blue';
		else if (strpos($trailnode->find('td',2)->outertext,'expert') != false)
			$trail['diff'] = 'black';
		else 
			$trail['diff'] = 'dblblack';
		
		$trail['area'] = 'Glades';

		$trailArray[] = $trail;
	}
}



// Gets ski bowl info
foreach($skiBowlTable->find('tr') as $trailnode) {
	if (strpos($trailnode->outertext,'bgcolor="#000000"') === false
		&& strpos($trailnode->outertext,'alt="easier"') === false
		&& strpos($trailnode->outertext,'alt="more difficult"') === false
		&& strpos($trailnode->outertext,'alt="most difficult"') === false)
	{

		// name checker
		$trail['name'] = $trailnode->plaintext;
		// groomed checker
		if (strpos($trailnode->find('td',0)->outertext,'img') != false)
			$trail['groomed'] = 'true';
		else 
			$trail['groomed'] = 'false';
        
		// open checker	
		if (strpos($trailnode->find('td',1)->outertext,'img') != false)
			$trail['open'] = 'true';
		else 
			$trail['open'] = 'false';
		
		// difficulty checker	
		if (strpos($trailnode->find('td',2)->outertext,'beginner') != false)
			$trail['diff'] = 'green';
		else if  (strpos($trailnode->find('td',2)->outertext,'intText') != false)
			$trail['diff'] = 'blue';
		else if (strpos($trailnode->find('td',2)->outertext,'expert') != false)
			$trail['diff'] = 'black';
		else 
			$trail['diff'] = 'dblblack';
		
		$trail['area'] = 'Northe Creek Ski Bowl';

		$trailArray[] = $trail;
	}
}

	
	
	
	
	
	
echo '<center>';
echo '<h1>Gore Trail Conditons!</h1>';
echo '</br>';
	
$currentArea = '';	
	
foreach ($trailArray as $a)
{
	// check to see if were still in the same area
	if ($currentArea === $a['area'])
	{
		echo $a['diff'].': '.$a['name'].' is ';
		if ($a['open'] === 'true')
		{
			echo 'Open ';
			if ($a['groomed'] === 'true')
				echo 'and groomed! </br>';
			else
				echo '</br>';
		}
		else if ($a['open'] === 'false')
			echo 'Closed =( </br>';
		else 
			echo '</br>';
	}
	else 
    {
        echo '<h1>'.$a['area'].'</h1>';
		echo $a['diff'].': '.$a['name'].' is ';
		if ($a['open'] === 'true')
		{
			echo 'Open ';
			if ($a['groomed'] === 'true')
				echo 'and groomed! </br>';
			else
				echo '</br>';
		}
		else if ($a['open'] === 'false')
			echo 'Closed =( </br>';
		else 
			echo '</br>';
	}

	
	$currentArea = $a['area'];
	
}
echo '</center>';
			

?>