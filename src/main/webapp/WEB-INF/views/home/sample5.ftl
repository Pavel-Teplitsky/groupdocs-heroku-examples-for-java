<#include "../include/common.ftl"/>
<#include "../include/header.ftl"/>

		<#assign menuItems = { "Home" : "/", "Contact" : "/contact", "About" : "/about" } />
		<@navBar menuItems=menuItems activeItem=0 brandTitle="GroupDocs" brandUrl="http://www.groupdocs.com" />  

        <div class="container">

            <div class="hero-unit">
	            <h2>GroupDocs Java SDK Samples for <a href="http://www.heroku.com/" target="_blank">heroku</a> add-on.</h2>

				<#assign sampleItems = {
					"Home Page" : "/", 
					"1 - How to login to GroupDocs using the API" : "/sample1", 
					"2 - How to list files within GroupDocs Storage using the Storage API" : "/sample2", 
					"3 - How to upload a file to GroupDocs using the Storage API" : "/sample3", 
					"4 - How to upload a file to GroupDocs using the Storage API" : "/sample4", 
					"5 - How to copy / move a file using the GroupDocs Storage API" : "/sample5", 
					"6 - How to add a Signature to a document in GroupDocs Signature" : "/sample6", 
					"7 - How to create a list of thumbnails for a document" : "/sample7", 
					"8 - How to return a URL representing a single page of a Document" : "/sample8", 
					"9 - How to generate an embedded Viewer URL for a Document" : "/sample9", 
					"10 - How to share a document to other users" : "/sample10"} />
				<@samplesBar barItems=sampleItems activeItem=5 />
				
                <p>
					<p>
						<div class="alert alert-info">This sample will show how to Copy/Move particular file in your GroupDocs account using Java SDK</div>
					</p>
					<#if errmsg?? >
						<p>
							<span class="label label-important">${errmsg}</span>
						</p>
					</#if>
					<form action="/sample5" method="POST" enctype="multipart/form-data">
						<div class="input-append">
							<label for="fileId">File ID</label>
							<input type="text" name="fileId" id="fileId" /><br />

							<label for="folderNameId">Folder in which you want to copy/move file </label>
							<input type="text" name="folderName" id="folderNameId" /><br />
							
							<input type="submit" value="Submit" class="btn" />
						</div>
					</form>
					<ul class="nav nav-tabs nav-stacked">
						<li><a>
							<ul class="thumbnails">
								<li class="span5">
									Source File
								</li>
								<li class="span5">
									Destination File
								</li>
							</ul>
						</a></li>
						<li><a>
							<ul class="thumbnails">
								<li class="span5">
									Name: 123
								</li>
								<li class="span5">
									Name: 123
								</li>
							</ul>
						</a></li>
						<li><a>
							<ul class="thumbnails">
								<li class="span5">
									Path: 234
								</li>
								<li class="span5">
									Path: 234
								</li>
							</ul>
						</a></li>
						<li><a>
							<ul class="thumbnails">
								<li class="span5">
									GUID: 345
								</li>
								<li class="span5">
									GUID: 345
								</li>
							</ul>
						</a></li>
						<li><a>
							<ul class="thumbnails">
								<li class="span5">
									ID: 456
								</li>
								<li class="span5">
									ID: 456
								</li>
							</ul>
						</a></li>
					</ul>
                </p>
            </div>

            <hr />

            <footer>
                <p>&copy; GroupDocs 2012</p>
            </footer>
        </div> <!-- /container -->

<#include "../include/foother.ftl"/>