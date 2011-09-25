function prefillClear(field) {
	if (field.defaultValue==field.value) {field.value = '';}
	else if (field.value == '') {field.value = field.defaultValue;}
}

function initPie(charities, total){
	
	var data = [];
	var series = charities;
	for( var i = 0; i<series; i++)	{
		
		var lbl;
				
		if (isNaN((parseFloat(total,0)/series))){
			lbl=0;
		}else{
			lbl=(parseFloat(total,0)/series).toFixed(2);;
		}
		
		data[i] = { label: "£"+ lbl, data: charities }
	}

	$.plot($("#heart-pie"), data, 
	{
		series: {
			pie: { 
				show: true,
				radius:300,
				label: {
					show: true,
					formatter: function(label, series){
						return '<div style=";bottom: 0px;left: 0px;font-size:8pt;padding:0;color:white;">'+label+'<br/>'+Math.round(series.percent)+'%</div>';
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


