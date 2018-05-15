<html>

    <#include "../common/head.ftl">
    <!--商品列表 -->
<body>

    <div id="wrapper" class="toggled">
          <!--边栏 sidebar  -->
           <#include  "../common/nav.ftl">
           
           
           
           
          <!--主要内容区域  -->
          <div id="page-content-wrapper"> 
            	<div class="container-fluid">
                     <div class="row clearfix">
							<div class="col-md-12 column">
								<form role="form"  method="post" action="/sell/category/save">
									<div class="form-group">
										 <label>名字</label>
										 <input type="text" name="categoryName" class="form-control" value="${(productCategory.categoryName)!''}" />
									</div>
									<div class="form-group">
										 <label>type</label>
										 <input type="number" name="categoryType" class="form-control" value="${(productCategory.categoryType)!''}" />
									</div>
									
								 <input hidden type="text" name="categoryId" value="${(productCategory.categoryId)!''}"> <!--把productId传过去-->
								 <button type="submit" class="btn btn-default">提交</button>
								</form>
							</div>
					 </div>
					                   
         
              </div>
          </div>
     
     
     </div>
	

</body>



</html>