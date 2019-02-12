    $(document).ready(function(){
    	var sPath = window.location.pathname;
    	var sPage = sPath.substring(sPath.lastIndexOf('/') + 1);
//    	alert(sPage);
    	
    	if (sPage == "resource.do") {
    		$("ul li ul #menu1").addClass("selected-item");
        }
    	if (sPage == "vcscfinfo.do") {
    		$("ul li ul #menu2").addClass("selected-item");
        }
    	if (sPage == "network.do") {
    		$("ul li ul #menu3").addClass("selected-item");
        }
    	if (sPage == "image.do" || sPage == "imageDelete.do" || sPage == "imageSave.do") {
    		$("ul li ul #menu4").addClass("selected-item");
        }
    	if (sPage == "flavor.do" || sPage == "deleteFlavor.do" || sPage == "createFlavor.do") {
    		$("ul li ul #menu5").addClass("selected-item");
        }
    	if (sPage == "package.do" || sPage == "deletePackageInfo.do" || sPage == "insertPackageInfo.do") {
    		$("ul li ul #menu6").addClass("acselected-itemtive");
        }
    	if (sPage == "vnfd.do" || sPage == "deleteVnfd.do" || sPage == "createVnfd.do") {
    		$("ul li ul #menu7").addClass("selected-item");
        }
    	if (sPage == "vnf.do" || sPage == "deleteVnf.do" || sPage == "createVnf.do") {
    		$("ul li ul #menu8").addClass("selected-item");
        }
    });