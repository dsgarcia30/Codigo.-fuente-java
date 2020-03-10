package com.ProductsBackend.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.ProductsBackend.demo.dao.daoProductos;
import com.ProductsBackend.demo.entity.Producto;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
public class productosController {

	private String img = "";

	@Autowired
	private daoProductos daoProducto;

	@GetMapping("products")
	public List<Producto> listaProductos() {
		List<Producto> list = new ArrayList<>();
		File archivo = new File("src/main/resources/ImgFile/");
		if (archivo.exists()) {
			for (Producto pro : daoProducto.findAll()) {
				Producto produc = new Producto();
				String encodeBase64 = null;
				try {
					File file = new File("src/main/resources/ImgFile/" + pro.getImg());
					String exten = file.getName();
					String extension = FilenameUtils.getExtension(exten);
					FileInputStream fileInputStream = new FileInputStream(file);
					byte[] bytes = new byte[(int) file.length()];
					fileInputStream.read(bytes);
					encodeBase64 = Base64.getEncoder().encodeToString(bytes);
					produc.setImg("data:image/" + extension + ";base64," + encodeBase64);
					produc.setNombre(pro.getNombre());
					produc.setCategoria(pro.getCategoria());
					produc.setEstado(pro.getEstado());
					produc.setFecha(pro.getFecha());
					produc.setId(pro.getId());
					fileInputStream.close();
					list.add(produc);
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		}
		return list;
	}


	@PostMapping("registerImg")
	public void registerImg(@RequestPart("file") MultipartFile file) throws IOException {
		if (!file.isEmpty()) {
			File archivo = new File("src/main/resources/ImgFile/");
			System.out.println(archivo.getAbsolutePath());
			System.out.println(archivo.exists());
			if (archivo.exists()) {
				byte[] bytes = file.getBytes();
				System.out.println("directorio si existe");
				img = file.getOriginalFilename();
				System.out.println(img);
				Path path = Paths.get(archivo.getAbsolutePath(), img);
				Files.write(path, bytes);
			}
//		

		}
	}

	@PostMapping("registerProducto")
	public Producto registerProducto(@RequestBody Producto producto) {
		producto.setImg(img);
		daoProducto.registerProduct(producto);
		return producto;
	}

}
