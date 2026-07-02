package cl.techstore.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.techstore.api.dto.ProductoDTO;
import cl.techstore.api.model.Producto;
import cl.techstore.api.repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private AuditoriaService auditoriaService;

    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    public Producto crear(ProductoDTO dto) {

        Producto producto = new Producto();

        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setCategoria(dto.getCategoria());
        producto.setActivo(true);

        Producto productoGuardado = productoRepository.save(producto);

        auditoriaService.enviarEvento(
                "CREAR",
                productoGuardado.getId(),
                productoGuardado.getNombre(),
                "admin@techstore.cl"
        );

        return productoGuardado;
    }

    public Producto modificar(Long id, ProductoDTO dto) {

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setCategoria(dto.getCategoria());

        Producto productoActualizado = productoRepository.save(producto);

        auditoriaService.enviarEvento(
                "MODIFICAR",
                productoActualizado.getId(),
                productoActualizado.getNombre(),
                "admin@techstore.cl"
        );

        return productoActualizado;
    }

    public void eliminar(Long id) {

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        producto.setActivo(false);

        Producto productoEliminado = productoRepository.save(producto);

        auditoriaService.enviarEvento(
                "ELIMINAR",
                productoEliminado.getId(),
                productoEliminado.getNombre(),
                "admin@techstore.cl"
        );
    }

    public List<Producto> listarActivos() {
        return productoRepository.findByActivoTrue();
    }
}