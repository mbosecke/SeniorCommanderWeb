var sc = sc || {};

$(function(){

    /**************************************
     * Collapsing sidebar
     **************************************/

    var CONFIG_COLLAPSED_SIDEBAR = 'collapsed-sidebar';

    $('.sidebar-toggle,#collapsed-sidebar').on('click', function(){

        $('.sidebar-toggle>.glyphicon').toggleClass('glyphicon-chevron-left glyphicon-chevron-right');
        $('#wrapper').toggleClass('includes-sidebar');
        //$('#sidebar-wrapper,#collapsed-sidebar').toggle();

        // persist the setting
        if(sc.util.getCookie(CONFIG_COLLAPSED_SIDEBAR)){
            sc.util.eraseCookie(CONFIG_COLLAPSED_SIDEBAR);
        }else{
            sc.util.setCookie(CONFIG_COLLAPSED_SIDEBAR, true);
        }
        return false;
    });


});