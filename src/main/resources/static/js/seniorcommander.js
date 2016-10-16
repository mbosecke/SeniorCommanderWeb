$(function(){


    /**************************************
     * Collapsing sidebar
     **************************************/

    var CONFIG_COLLAPSED_SIDEBAR = 'collapsed-sidebar';

    var toggleSidebar = function(){
        $('.sidebar-toggle>.glyphicon').toggleClass('glyphicon-chevron-left glyphicon-chevron-right');
        $('#wrapper').toggleClass('includes-sidebar');
        $('#sidebar-wrapper,#collapsed-sidebar').toggle();
    }

    $('.sidebar-toggle,#collapsed-sidebar').on('click', function(){

        toggleSidebar();

        // persist the state in local storage
        if(localStorage[CONFIG_COLLAPSED_SIDEBAR]){
            localStorage.removeItem(CONFIG_COLLAPSED_SIDEBAR);
        }else{
            localStorage[CONFIG_COLLAPSED_SIDEBAR] = true;
        }
        return false;
    });

    if(localStorage[CONFIG_COLLAPSED_SIDEBAR]){
        toggleSidebar();
    }


});