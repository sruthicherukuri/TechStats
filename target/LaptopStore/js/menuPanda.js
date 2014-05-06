(function ($) {

    var uuid = 0;
    var menuIndex = 0;
    var panel;
    var panels = new Array();
    var open, subopen;
    var defaultTime = 350;

    $.fn.pandfy = function () {

        //atribui uma variável com todo o menu;
        //var uniqID = uniqueID();
        var uniqID = "sideMenuID";
        var contents = $(this).clone();

        //cria 2 divs para serem os wrappers do menu e do conteúdo respectivamente
        $("body").append("<div class='mp-layer' id='" + uniqID + "'></div>");
        $("body").append("<div class='mp-wrapper' id='" + uniqID + "'></div>");
        $("body").children().each(function (index, elemenet) {
            if ((!$(elemenet).hasClass("mp-layer")) && (!$(elemenet).hasClass("mp-wrapper")))
                $(elemenet).detach().appendTo(".mp-wrapper");
        });
        //monta o botão do menu
        this.html("<img src='' class='mp-buttom'>");
        //$("#buttonPlaceholder").append("<a id='viewMenuBtn' class='btn btn-large btn-primary' style='z-index:9999;'>View Menu</a>");
        $("#buttonPlaceholder").append("<a id='Ul1' href='#'>&lt;&lt;&nbsp;Compare Now</a>");

        //prepara o id unico para o painel principal
        panel = $("#" + uniqID);

        $("ul", contents).each(function (index, obj) {
            //atribui um id unico para todos os elementos
            $(obj).attr("id", uniqueID());
            //cria um array com todos os submenus;
            panels.push($(obj));
        });

        for (var i = 0; i < panels.length; i++) {
            //Cria todos os menus
            makePage(i, 0);
        }

        //faz o painel com o slide de chamada
        submExpand(0, menuIndex);

        colapse(0);

        $(".mp-layerlink a").click(function (e) {
            e.preventDefault();
            var link = $(this).data("linkto");
            var el = $("div[data-relativeid='" + link + "']")
            submExpand(defaultTime, "#" + el.attr("id"));
        })

        $(window).resize(function () {
            colapse(50);
        });

        $("#Ul1").click(function () {
            if (open) {
                colapse(defaultTime);
            }
            else {
                expand(defaultTime);
            }

        });


        $(".mp-layer h1").click(function () {
            var el = $(this).parent();
            var index = findIndex($(el).data("relativeid"))
            if (!index == 0) {
                var panel = el;
                submColapse(defaultTime, panel);
            }
            else {
                colapse(defaultTime);
            }

        });


    };

    function uniqueID() {
        return "mp-id-" + (++uuid);
    }



    function submColapse(time, el) {

        var w = $(".mp-layer").width();
        $(el).clearQueue().animate({
            left: "-" + w

        }, time);

        subopen = false;
    }

    function submExpand(time, el) {
        var w = $(".mp-layer").width();
        $(el).clearQueue().animate({
            left: 0
        }, time);

        subopen = true;
    }



    function colapse(time) {
        var w = $(panel).width();
        $(panel).clearQueue().animate({ left: "-" + w }, time);
        $(".mp-wrapper").clearQueue().animate({ left: 0 }, time);
        open = false;
    }

    function expand(time) {
        var w = $(panel).width();
        $(panel).clearQueue().animate({ left: 0 }, time);
        $(".mp-wrapper").clearQueue().animate({ left: w }, time);
        open = true;
    }

    function makePage(idPanel, time) {
        var uniq = uniqueID();
        var subPanel;
        var subContent;
        var relativeId;
        var c;

        var w = $(".mp-layer").width();
        subContent = panels[idPanel];
        c = subContent.children();
        relativeId = subContent.attr("id");

        $(panel).append("<div data-relativeid='" + relativeId + "' id=" + uniq + " class='mp-subcontent mp-layer'></div>");
        subPanel = $("#" + uniq);

        subPanel.css("left", w + "px").css("width", w + "px");
        subPanel.append("<h1>" + subContent.data("title") + "</h1>");


        c.each(function (index, obj) {
            var item = $(obj);
            if (item.is("li")) {
                subPanel.append(item);
            }
            else if (item.is("ul")) {
                var name = item.data("title");
                var link = item.attr("id");
                subPanel.append("<li class='mp-layerlink' ><a data-linkto='" + link + "' href='#' >" + name + "</a></li>");
            }
        });

        submExpand(time, subPanel);

    }


    function findIndex(id) {
        var retorno = 0;
        $(panels).each(function (obj, index) {
            if ($(index).attr("id") == id) {
                retorno = obj;
            }
        });

        return retorno;
    }

}(jQuery));

