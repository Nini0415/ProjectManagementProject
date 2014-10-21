<meta charset="utf-8">

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

  <link rel="shortcut icon" href="favicon.gif">
  <!---CSS Files-->
  <link rel="stylesheet" href="css/master.css">
  <link rel="stylesheet" href="css/login.css">
  <link rel="stylesheet" href="css/icons.css">
  <link rel="stylesheet" href="css/tables.css">
  <link rel="stylesheet" href="css/iphone-check.css">
  <link href="js/sh/shThemeDefault.css" rel="stylesheet" type="text/css" />
  <link href="js/sh/shCore.css" rel="stylesheet" type="text/css" />
  
  
  
  <!---jQuery Files-->
  <script src="js/jquery-1.7.1.min.js"></script>
  <script src="js/jquery-ui-1.8.17.min.js"></script>
  <script src="js/jquery.spinner.js"></script>
  <script type="text/javascript" src="js/forms/jquery.validate.min.js"></script>
  <script src="js/styler.js"></script>
  <script src="js/jquery.tipTip.js"></script>
  <script src="js/colorpicker.js"></script>
  <script src="js/sticky.full.js"></script>
  <script src="js/global.js"></script>
  <script src="js/forms/fileinput.js"></script>
  <script src="js/forms/iphone-check.js"></script>
  <script src="js/forms/jquery.validate.min.js"></script>
  <script src="js/sh/shCore.js" type="text/javascript"></script>
  <script src="js/sh/shBrushXml.js" type="text/javascript"></script>
  <!---Fonts-->
  <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700' rel='stylesheet' type='text/css'>
  <!--[if lt IE 9]>
  <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->
    <!---FadeIn Effect, Validation and Spinner-->
  <script>
    $(document).ready(function () {
       $('div.wrapper').hide();
        $('div.wrapper').fadeIn(1200);
        $('#lg-form').validate();
        $('.submit').click(function() {
        var $this = $(this);
        $this.spinner();
        setTimeout(function() {
                $this.spinner('remove');
        }, 1000);
       });
    });
  </script>
  
