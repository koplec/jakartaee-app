package prv.koplec.jaxrs_todo.resource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;


@Path("/files")
public class FileResource {

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(@FormDataParam("file") InputStream fileInputStream,
            @FormDataParam("file") FormDataContentDisposition fileMetaData) throws  IOException{
        String tempFolderPath = System.getProperty("java.io.tmpdir");
        String fileName = fileMetaData.getFileName();
        java.nio.file.Path uploadedFileLocationPath = Paths.get(tempFolderPath, fileName);
        // save the file to the server
        Files.copy(fileInputStream, uploadedFileLocationPath, StandardCopyOption.REPLACE_EXISTING);
        String output = "File uploaded to : " + uploadedFileLocationPath.toString();
        return Response.status(200).entity(output).build();
    }

}
