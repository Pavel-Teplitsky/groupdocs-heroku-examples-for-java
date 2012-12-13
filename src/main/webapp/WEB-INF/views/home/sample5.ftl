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
					"4 - How to download a file from GroupDocs Storage using the Storage API" : "/sample4", 
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
							<label for="srcPathId">Source Path </label>
							<input type="text" name="srcPath" id="srcPathId" placeholder="myDoc.doc" /><br />

							<label for="dstPathId">Destination Path </label>
							<input type="text" name="dstPath" id="dstPathId" placeholder="test/newDoc.doc" /><br /><br />

							<label for="copyId">
								<input type="radio" name="action" value="copy" id="copyId" checked /> Copy
							</label>

							<label for="moveId">
								<input type="radio" name="action" value="move" id="moveId" /> Move
							</label>
							
							<input type="submit" value="Submit" class="btn" />
						</div>
					</form>
					<#if moveResult??  && (moveResult.src_file?? || moveResult.dst_file??)>
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
										<#if moveResult.src_file?? >
											<span class="label">Name:</span> ${moveResult.src_file.name}
										</#if>
									</li>
									<li class="span5">
										<#if moveResult.dst_file?? >
											<span class="label">Name:</span> ${moveResult.dst_file.name}
										</#if>
									</li>
								</ul>
							</a></li>
							<li><a>
								<ul class="thumbnails">
									<li class="span5">
										<#if moveResult.src_file?? >
											<span class="label">Path:</span> ${moveResult.src_file.document_path}
										</#if>
									</li>
									<li class="span5">
										<#if moveResult.dst_file?? >
											<span class="label">Path:</span> ${moveResult.dst_file.document_path}
										</#if>
									</li>
								</ul>
							</a></li>
							<li><a>
								<ul class="thumbnails">
									<li class="span5">
										<#if moveResult.src_file?? >
											<span class="label">GUID:</span> ${moveResult.src_file.guid}
										</#if>
									</li>
									<li class="span5">
										<#if moveResult.dst_file?? >
											<span class="label">GUID:</span> ${moveResult.dst_file.guid}
										</#if>
									</li>
								</ul>
							</a></li>
							<li><a>
								<ul class="thumbnails">
									<li class="span5">
										<#if moveResult.src_file?? >
											<span class="label">ID:</span> ${moveResult.src_file.id}
										</#if>
									</li>
									<li class="span5">
										<#if moveResult.dst_file?? >
											<span class="label">ID:</span> ${moveResult.dst_file.id}
										</#if>
									</li>
								</ul>
							</a></li>
						</ul>
					<#elseif moveResult?? >
						<div class="alert alert-success">
							Operation complete.
						</div>
					</#if>
                </p>
            </div>

            <hr />

            <footer>
                <p>&copy; GroupDocs 2012</p>
            </footer>
        </div> <!-- /container -->

<#include "../include/foother.ftl"/>