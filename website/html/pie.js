function initPie(){
	var data = [];
	var series = Math.floor(Math.random()*10)+1;
	for( var i = 0; i<series; i++)
	{
		data[i] = { label: "Series"+(i+1), data: Math.floor(Math.random()*100)+1 }
	}

	$.plot($("#heart-pie"), data, 
	{
		series: {
			pie: { 
				show: true,
				radius:300,
				label: {
					show: false,
					formatter: function(label, series){
						return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'+label+'<br/>'+Math.round(series.percent)+'%</div>';
					},
					threshold: 0.1
				}
			}
		},
		legend: {
			show: false
		}
	});	
}	

function submitToServer(){
  	var params = "";
	$('input[@type=checkbox]:checked').each(function(key, val){
		$("#results").append('<li>['+key+'] Name=['+val.name+', ID='+val.value+']</li>');
	});
	
    var charities = $('input[@type=checkbox]:checked').map(function(i,n) {
        return $(n).val();
    }).get(); //get converts it to an array

    if(charities.length == 0) { 
        charities = "none"; 
    }
	
	var total = $('#amount').val();
	var increment = total/charities.length;
	
	
	var url = "http://google.com";
    $.post(url, {'charities[]': charities, 'increment': increment}, function(response) {});
};
