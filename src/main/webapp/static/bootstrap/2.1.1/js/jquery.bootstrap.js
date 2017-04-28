/**
* jquery.bootstrap.js
Copyright (c) Kris Zhang <kris.newghost@gmail.com>
License: MIT (https://github.com/newghost/bootstrap-jquery-plugin/blob/master/LICENSE)
*/
/* Extend string method */

/*
string.format, ref: http://stackoverflow.com/questions/610406/javascript-equivalent-to-printf-string-format/4673436#4673436
*/
if (!String.prototype.format) {
  String.prototype.format = function() {
    var args = arguments;
    return this.replace(/{(\d+)}/g, function(match, number) { 
      return typeof args[number] != 'undefined'
        ? args[number]
        : match
      ;
    });
  };
}/*
Description: $.fn.dialog
Author: Kris Zhang
require: 
  string.format.js
*/
(function($) {

  $.fn.dialog = function(options) {

    var self    = this
      , $this   = $(self)
      , $body   = $(document.body)
      , $msgbox = $this.closest('.dialog')
      , parentDataName = 'dialog-parent'
      , arg1    = arguments[1]
      , arg2    = arguments[2]
      ;

    var create = function() {

      var msghtml
        = ''
        + '<div class="dialog modal fade">'
        + '<div class="modal-dialog">'
        +   '<div class="modal-content">'
        +     '<div class="modal-header">'
        +         '<button type="button" class="close">&times;</button>'
        +         '<h4 class="modal-title"></h4>'
        +     '</div>'
        +     '<div class="modal-body"></div>'
        +     '<div class="modal-footer"></div>'
        +   '</div>'
        + '</div>'
        + '</div>'
        ;


      $msgbox = $(msghtml);
      $(document.body).append($msgbox);
      $msgbox.find(".modal-body").append($this);
    };

    var createButton = function(_options) {
      var buttons = (_options || options || {}).buttons || {}
        , $btnrow = $msgbox.find(".modal-footer");

      //clear old buttons
      $btnrow.html('');

      for (var button in buttons) {
        var btnObj  = buttons[button]
          , id      = ""
          , text    = ""
          , classed = "btn-default"
          , click   = "";

        if (btnObj.constructor == Object) {
          id      = btnObj.id;
          text    = btnObj.text;
          classed = btnObj['class'] || btnObj.classed || classed;
          click   = btnObj.click;
        }

        if (btnObj.constructor == Function) {
          text  = button;
          click = btnObj;
        }

        //<button data-bb-handler="danger" type="button" class="btn btn-danger">Danger!</button>
        $button = $('<button type="button" class="btn {1}">{0}</button>'.format(text, classed));

        id && $button.attr("id", id);
        if (click) {
          (function(click) {
            $button.click(function() {
              click.call(self);
            });
          })(click);
        }

        $btnrow.append($button);
      }

      $btnrow.data('buttons', buttons);
    };

    var show = function() {
      // call the bootstrap modal to handle the show events (fade effects, body class and backdrop div)
      $msgbox.modal('show');
    };

    var close = function(destroy) {
      // call the bootstrap modal to handle the hide events and remove msgbox after the modal is hidden
      $msgbox.modal('hide').on('hidden.bs.modal', function() {
                if (destroy) {
                    $this.data(parentDataName).append($this);
                    $msgbox.remove();
                }
            });
    };

    if (options.constructor == Object) {
      !$this.data(parentDataName) && $this.data(parentDataName, $this.parent());

      if ($msgbox.size() < 1) {
        create();
      }
      createButton();
      $(".modal-title", $msgbox).html(options.title || "");
      $(".modal-header .close", $msgbox).click(function() {
        var closeHandler = options.onClose || close;
        closeHandler.call(self);
      });
      (options['class'] || options.classed) && $msgbox.addClass(options['class'] || options.classed);
      options.autoOpen !== false && show();
    }

    if (options == "destroy") {
      close(true);
    }

    if (options == "close") {
      close();
    }

    if (options == "open") {
      show();
    }

    if (options == "option") {
      if (arg1 == 'buttons') {
        if (arg2) {
          createButton({ buttons: arg2 });
          show();
        } else {
          return $msgbox.find(".modal-footer").data('buttons');
        }
      }
    }

    return self;
  };

})(jQuery);
/*
Description: $.messager
Author: Kris Zhang
require: 
  string.format.js
  $.fn.dialog
*/

$.messager = (function() {

  var alert = function(title, message) {
    var model = $.messager.model;

    if (arguments.length < 2) {
      message = title || "";
      title   = "&nbsp;"
    }

    $("<div>" + message + "</div>").dialog({
        title:   title
        // override destroy methods;
      , onClose: function() {
          $(this).dialog("destroy");
        }
      , buttons: [{
            text: model.ok.text
          , classed: model.ok.classed || "btn-success"
          , click: function() {
              $(this).dialog("destroy");
            }
        }]
    });
  };

  var confirm = function(title, message, callback) {
    var model = $.messager.model;

    $("<div>" + message + "</div>").dialog({
        title:   title
        // override destroy methods;
      , onClose: function() {
          $(this).dialog("destroy");
        }
      , buttons: [{
            text: model.ok.text
          , classed: model.ok.classed || "btn-success"
          , click: function() {
              $(this).dialog("destroy");
              callback && callback();
            }
        },
        {
            text: model.cancel.text
          , classed : model.cancel.classed || "btn-danger"
          , click: function() {
              $(this).dialog("destroy");
            }
        }]
    });
  };

  return {
      alert:   alert
    , confirm: confirm
  };

})();


$.messager.model = {
    ok: { text : "OK", classed : 'btn-success' },
    cancel: { text : "Cancel", classed : 'btn-danger' }
};
/*
Description: $.fn.datagrid
Author: Kris Zhang
require: 
  string.format.js
*/

(function($) {

  $.fn.datagrid = function(method, options) {

    var self          = this
      , selectedClass = "warning"
      , $this         = $(this)
      ;

    var bindRows = function($rows) {
      var conf          = $this.data("config")
        , selectChange  = conf.selectChange
        , singleSelect  = conf.singleSelect
        , edit          = conf.edit
        ;

      var selectHandler = function(e) {
        var $row              = $(this)
          , hasSelectedClass  = $row.hasClass(selectedClass)
          , idx               = $("tbody tr", $this).index($row)
          , row               = $this.data("rows")[idx] || {}
          ;

        //rows may added dynamiclly
        singleSelect && $("tbody tr", $this).removeClass(selectedClass);
        $row.toggleClass(selectedClass);

        //API selectChange: function( selected, rowIndex, rowData )
        selectChange && selectChange(true, idx, row, $row);
      };
      (selectChange || typeof singleSelect != "undefined") && $rows.click(selectHandler);


      var editHandler = function(e) {
        var $input  = $(this)
          , $row    = $input.closest("tr")
          , idx     = $("tbody tr", $this).index($row)
          , row     = $this.data("rows")[idx] || {}
          , name    = $input.attr("name")
          ;

        name && (row[name] = $input.val());
      }
      edit && $rows.find("input").keyup(editHandler);
    };

    var getRow = function(columns, row, conf, idx) {
      var trow = "<tr>";
      for (var j = 0, m = columns[0].length; j < m; j++) {
    	 
        var column = columns[0][j]
          , format = column.formatter
          , hidden = column.hidden
          , field  = column.field
          , tip    = column.tip
          , value  = row[field]
          , maxlength = column.maxlength
          , readonly  = column.readonly
          ;

        typeof value == "undefined" && (value = "");

        if (conf.edit) {
          maxlength = maxlength
            ? ' maxlength="{0}"'.format(column.maxlength)
            : '';

          readonly  = readonly ? ' readonly="readonly"' : '';

          value
            = '<input name="{0}" value="{1}" class="form-control"{2}{3}/>'.format(
                column.field
              , value
              , maxlength
              , readonly
            );
        }

        //if it has 'formatter' attribute override the content
        value = format ? format(row[field], row, idx) : value;
        var flag=hidden==true?"hidden":"";
        trow = trow + "<td "+flag+">" + value + "</td>";
      };
      trow += "</tr>";
      return trow;
    };

    var getData = function(edit) {
      if (!options) return;

      var config  = $this.data("config") || {}
        , columns = config.columns
        , rows    = options.rows || options;

      var body = "<tbody>";
      if (rows) {
        for (var i = 0, l = rows.length; i < l; i++) {
          body += getRow(columns, rows[i], config , i);
        }
      }
      body += "</tbody>";

      $("tbody", $this).remove();
      $this
        .data("rows", rows)
        .append(body);

      //add "edit" class if it's edit mode.
      config.edit && $this.addClass("edit");
      //rebind events
      bindRows($("tbody tr", $this));
    };

    //handle: $().datagrid({column: [[]]})
    if (method && method.constructor == Object) {
      var columns = method.columns;
 
      if (columns) {
        $("thead", $this).size() < 1
          && $this.append("<thead></thead>");

        var header = "<tr>";
        //method.del && (header += "<td></td>");
        for (var i = 0, l = columns[0].length; i < l; i++) {
          var col = columns[0][i],hidden=col.hidden;
          var flag=hidden==true?"hidden":"";
          header += "<th "+flag+">" + (col.title || "") + "</th>";
        }
        header += "</tr>";

        $this.data("config",  method);
        $("thead", $this).html(header);
      }
    }

    //handle: $().datagrid("loadData", {rows: []}) or $().data("loadData", [])
    if (method == "loadData") getData();

    if (method == "getData") {
      return $this.data("rows");
    }

    if (method == "getConfig") {
      return $this.data("config");
    }

    if (method == "getColumns") {
      return $this.data("config").columns;
    }

    if (method == "unselectRow") {
      typeof options != "undefined"
        ? $("tbody tr", $this).eq(options).removeClass(selectedClass)
        : $("tbody tr", $this).removeClass(selectedClass);
    }

    if (method == "updateRow") {
      var idx     = options.index
        , conf    = $this.data("config")
        , rows    = $this.data("rows")
        , row     = options.row
        , columns = conf.columns
        ;

      if (rows) {
        row = $.extend(rows[idx], row);
        $this.data("rows", rows);
      }

      var $row = $(getRow(columns, row, conf,idx));

      $("tbody tr", $this).eq(idx)
        .after($row)
        .remove();

      bindRows($row);
    }

    if (method == "getSelections") {
      var rows    = $this.data("rows")
        , selRows = [];

      $("tbody tr", $this).each(function(idx) {
        $(this).hasClass(selectedClass) && selRows.push(rows[idx]);
      });

      return selRows;
    }

    if (method == "insertRow") {
      var idx   = options.index || 0
        , row   = options.row
        , conf  = $this.data("config")
        , rows  = $this.data("rows") || []
        ;

      if (!conf || !row) return $this;

      var $rows  = $("tbody tr", $this)
        , $row   = $(getRow(conf.columns, row, conf, idx))
        , $tar   = $rows.eq(idx)
        ;

      bindRows($row);
      $tar.size() ? $tar.before($row) : $("tbody", $this).append($row);
      rows.splice(idx, 0, row);
    }

    if (method == "deleteRow") {
      if (options > -1) {
        $("tbody tr", $this).eq(options).remove();
        var rows = $this.data("rows");
        rows.splice(options, 1);
      }
    }

    return self;
  };


})(jQuery);/*
Dependence: string.js
*/

/*
Dependence: string.js
*/

(function($) {

	  $.fn.tree = function(method, options) {

	    var self        = this
	      , $this       = $(self)
	      , pushFn      = Array.prototype.push
	      , icon_node   = "icon-file-alt"
	      , icon_open   = "icon-folder-open"
	      , icon_close  = "icon-folder-close"
		  , selectedClass = "tree_node_selected"
	      ;
	    if ((navigator.userAgent.indexOf('MSIE') >= 0) && (navigator.userAgent.indexOf('Opera') < 0)){
	    	flag = true;
	    }
	    var build = function(data, root, parentState) {
	      var tree = [];
	      !root && tree.push('<ul style="display:{0}">'.format(parentState == "close" ? "none" : "block"));

	      for (var i = 0, l = data.length; i < l; i++) {
	        var node        = data[i]
	          , children    = node.children
	          , id          = node.id
	          , state       = node.state
	          , attributes  = node.attributes
	          ;

	        tree.push('<li class="treewrap">');
	        if (1) {
	          var type
	            = typeof children == "undefined"
	            ? icon_node
	            : ( state == "close" ?  icon_close : icon_open );
	          tree.push('<span class="icon {0}"></span> '.format(type));
	        }
	        tree.push('<a{1}{2}{3}>{0}</a>'.format(
	            node.text
	          , children   ? " class='tree-parent'" :  " class='tree-node'" 
	          , id         ? " data-id='{0}'".format(id) : ""
	          , attributes ? " data-attr='{0}'".format(JSON.stringify(attributes)) : ""
	        ));
	        children && pushFn.apply(tree, build(children, false, state));
	        tree.push('</li>');
	      };
	      !root && tree.push('</ul>')

	      return tree;
	    };

	    var bind = function() {
	      $("span.icon-folder-open, span.icon-folder-close", $this).click(function(e) {
	        var $icon     = $(this)
	          , $children = $icon.closest("li").children("ul");
	        if ($icon.hasClass(icon_close)){
	          $icon.removeClass(icon_close).addClass(icon_open);
	          $children.show();
	        } else {
	          $icon.removeClass(icon_open).addClass(icon_close);
	          $children.hide();
	        }
	      });
		  $("a.tree-parent , a.tree-node", $this).click(function(e) {
			var $icon     = $(this)
				, conf = $this.data("config")
				, singleSelect = conf.singleSelect
				;
			if(singleSelect == undefined) return false;
	        if ( singleSelect ){
				if( $icon.hasClass(selectedClass)){
        			$icon.removeClass(selectedClass);
        		} else {
        			$('a',$this).removeClass(selectedClass);
        			$icon.addClass(selectedClass);
        		}
	        } else {// parent_node click or tree_node click  ; 可扩展onlyLeafCheck
	        	if($icon.hasClass('tree-parent') && $icon[0].getAttribute('data-id')){
	        		if($icon.hasClass(selectedClass)){
	        			$icon.removeClass(selectedClass)
	        			$icon.closest("li").children("ul").children().find('a').removeClass(selectedClass)
	        		}else{
	        			$icon.addClass(selectedClass)
	        			$icon.closest("li").children("ul").children().find('a').removeClass(selectedClass).addClass(selectedClass);
	        		}
	        	}else{
	        		if( $icon.hasClass(selectedClass)){
	        			$icon.removeClass(selectedClass);
	        		} else 
	        			$icon.addClass(selectedClass);
	        	}
	        }
	      });
	    };

	    if (method && method.constructor == Object) {
	      var data = method.data;
	      if (data && (data.constructor == Array)) {
	        var htmlArr = build(data, true);
	        $this.html(htmlArr.join(''));
	        $this.data("config", method);
	        bind();
	      }
		
	      var clickHandler = method.onClick;
	      if (clickHandler) {
	        $("li>a", $this).click(function() {
	          var $link = $(this);
	              attrs = $link.attr("data-attr");

	          clickHandler.call(self, {
	              id          : $link.attr("data-id")
	            , attributes  : attrs ? JSON.parse(attrs) : {}
	            , text        : $link.text()
	          }, $link);
	        });
	      }
	    }
	    if (method == "getSelections") {
			var selNodes = [];
			  $("a", $this).each(function(idx) {
				  if($(this).hasClass(selectedClass)){
					  var attrs =  $(this)[0].getAttribute('data-attr');
					  var object = {};
					  object['id'] = $(this)[0].getAttribute('data-id');
					  object['text'] = $(this)[0].text;
					  object['attributes'] = attrs ? JSON.parse(attrs) : {} ;
					  selNodes.push(object)
				  }
		      });
			  return selNodes;
		}
	    if (method == "find") {
	    	var selNodes = [];
	    	if(!options) return selNodes;
			$("a", $this).each(function(idx) {
				var id = $(this)[0].getAttribute('data-id')
					,attrs =$(this)[0].getAttribute('data-attr');
				if (options == id ) {
					var object = {};
					object['id'] = id;
					object['text'] = $(this)[0].text;
					object['attributes'] = attrs ? JSON.parse(attrs) : {};
					selNodes.push(object)
				}
			});
			return selNodes;
		}

	    if (method == "select") {
	    	if(!options || options.length == 0) return false;
	    	$('a', $this).removeClass(selectedClass);
			$("a", $this).each(function(idx) {
				for ( var int = 0; int < options.length; int++) {
					var  id = $(this)[0].getAttribute('data-id');
					if (id && id == options[int]) {
						$(this).toggleClass(selectedClass);
					}
				}
			});
			return true;
		}
	    
	    if (method == "getParent") {
	    	var selNodes = null 
	    		,obj = {}
	    		,datas = $this.data('config').data;
	    	if(!options || options.length == 0) return false;
	    	var getChildNode = function(options,datas,parentNode){
	    		for (var i = 0, l = datas.length; i < l; i++) {
	    			var node        = datas[i]
	    			, children    = node.children
	    			, id          = node.id
	    			;
	    			if(id == options) {
	    				selNodes=parentNode;
	    				return ;
	    			}
	    			if(children && !selNodes){
	    				getChildNode(options,children, node);
	    			}
	    		}
	    	};
	    	getChildNode(options,datas, null);
	    	if(selNodes){
	    		obj['id'] = selNodes.id;
	    		obj['text'] = selNodes.text;
	    		obj['attributes'] = selNodes.attributes;
	    	}
			return obj;
		}
	    if (method == "getChildren") {
	    	var selNodes =null
	    		 ,datas = $this.data('config').data;
	    	if(!options || options.length == 0) return false;
	    	var getChild = function(options,datas){
	    		for (var i = 0, l = datas.length; i < l; i++) {
	    			var node        = datas[i]
	    			, children    = node.children
	    			, id          = node.id
	    			;
	    			if(id == options) {
	    				selNodes=children;
	    				return ;
	    			}
	    			if(children && !selNodes){
	    				getChild(options,children);
	    			}
	    		}
	    	};
	    	getChild(options,datas);
			return selNodes;
		}
	    return self;
	  };

	})(jQuery);