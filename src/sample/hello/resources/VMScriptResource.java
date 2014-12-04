package sample.hello.resources;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import sample.hello.bean.VMScript;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;


@Path("/vmScript")
public class VMScriptResource {
	
	
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(@FormDataParam("uid") String uid,
			@FormDataParam("ip") String ip,
			@FormDataParam("upload") InputStream is,
			@FormDataParam("upload") FormDataContentDisposition formData) {
		String fileLocation = "c:/" + formData.getFileName();
		try {
			File f = saveFile(is, fileLocation);
			String result = "Successfully File Uploaded on the path "
					+ fileLocation;
			VMScript vs = new VMScript();
			if(vs.sendExeVmScriptMsg(uid, ip, f)){
				if (f.isFile() && f.exists()) {   
				    f.delete();   
				}
				return Response.ok("the send script is already executing ").build();
			}
			return Response.ok("send script failed").build();
		} catch (IOException e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	private File saveFile(InputStream is, String fileLocation)
			throws IOException {
		File f = new File(fileLocation);
		OutputStream os = new FileOutputStream(f);
		byte[] buffer = new byte[256];
		int bytes = 0;
		while ((bytes = is.read(buffer)) != -1) {
			os.write(buffer, 0, bytes);
		}
		return f;
	}

	
}
