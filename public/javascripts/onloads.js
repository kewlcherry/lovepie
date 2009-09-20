$(document).ready(function () {
    // Attach the filter to our input and list
    My.List.Filter('input#search_filter_cause', '#filtered-cause>ul>li>span');
    My.List.Filter('input#search_filter_org', '#filtered-org>ul>li>span.name');
	  $("#tag-data").tabs({ fx: { opacity: 'toggle' } }).tabs();

		var containerHeight = $("ul#invisible-overlay").height();
		var heartItems = $('ul#invisible-overlay');
		var currentItemIndex = 0;
		var lastIndex = 0;
		var minSliceSize = 30;
		var initialSizeOfSlice = 0;
		var bottomOfContainer = 313;
		var lastHeightSubtraction = 0;

		$("#invisible-overlay li.one").resizable({ containment: '#invisible-overlay', alsoResize: '#beneath-image li.one', maxWidth: 699, minWidth: 699, minHeight: 30 });
		$("#invisible-overlay li.two").resizable({ containment: '#invisible-overlay', alsoResize: '#beneath-image li.two', maxWidth: 699, minWidth: 699, minHeight: 30});	
		$("#invisible-overlay li.three").resizable({ containment: '#invisible-overlay', alsoResize: '#beneath-image li.three', maxWidth: 699, minWidth: 699, minHeight: 30});

		$("#invisible-overlay li").resize(function(){
			$.fn.adjustLastElements();
		});	

		$.fn.getFirstResizableIndex = function(listObj) {
			var index = listObj.length;

			do {
				console.log("looping index is " + index -1 + "height =" + listObj.get(index -1));
				if(listObj.get(index -1).height() > minSliceSize){
					return index;
				}
				index--;
			} while (index > 0);
			return -1;		
		}

		$.fn.adjustLastElements = function() {
	//		var index = $.fn.getFirstResizableIndex($("#beneath-image li"));		
			if($("#beneath-image li:last").height() != minSliceSize){
				var allButLastElementHeight = parseInt($("#beneath-image li:last").prev().position().top) + parseInt($("#beneath-image li:last").prev().height())
				var diff = containerHeight - allButLastElementHeight;	
				$("#beneath-image li:last, #invisible-overlay li:last").height(diff);				
			}else{

			}
		}

	/*
	*	The max height is the furthest point down this element could be pulled
	*	including the previous and following slices.
	*
	*/
		$.fn.findMax = function() {
			var maxHeight = 0;					
			jQuery.each($('ul#invisible-overlay li').slice(0, currentItemIndex), function() {
			      maxHeight =  maxHeight + $(this).height() ;
			});

		  	var minPossHeightOfFollowers =   lastIndex - currentItemIndex;
			minPossHeightOfFollowers = minPossHeightOfFollowers * minSliceSize;
			maxHeight = maxHeight + minPossHeightOfFollowers;

			$('#invisible-overlay li').resizable('option', 'maxHeight', (containerHeight - maxHeight));
		}

		$('#invisible-overlay li').bind('resizestart', function(event, ui) {
			currentItemIndex = $(this).parent().children().index( this )
			$last = $('ul#invisible-overlay li:last');
		  	lastIndex = $(this).parent().children().index( $last );
			initialSizeOfSlice = $(this).height();

			$.fn.findMax();
		});	
	});