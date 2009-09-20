    var My = {};
    My.List = {
        Filter : function (inputSelector, listSelector) {

            // Sanity check
            var inp, rgx = new RegExp(), titles = $(listSelector), keys;
            if (titles.length === 0) {
                return false;
            }

            // The list with keys to skip (esc, arrows, return, etc)
            // 8 is backspace, you might want to remove that for better usability
            keys = [ 13, 27, 32, 37, 38, 39, 40 ];

            // binding keyup to the unordered list
            $(inputSelector).bind('keyup', function (e) {
                if (jQuery.inArray(e.keyCode, keys) >= 0) {
                    return false;
                }

                // Building the regex from our user input, 'inp' should be escaped
                inp = $(this).attr('value');
                try {
                    rgx.compile(inp, 'im');

                    // a MUCH faster implementation, thanks to Boy Baukema
                    for (var i = 0; i < titles.length; i++) {
                        if (rgx.source !== '' && !rgx.test(titles[i].innerHTML)) {
                            titles[i].parentNode.style.display = 'none';
                        } else {
                            titles[i].parentNode.style.display = '';
                        }
                    }
                    // Silently ignore possible bad regex
                } catch(e) {
                }
            });
        }
    };