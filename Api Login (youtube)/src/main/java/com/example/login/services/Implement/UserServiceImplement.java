package com.example.login.services.Implement;

import com.example.login.entities.UserEntity;
import com.example.login.models.User;
import com.example.login.repositories.jpa.UserJpaRepository;
import com.example.login.services.UserServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/*@Autowired la uso para poder utilizar los atributos sin inicializarlos con un new
inyeccion de dependencias*/

@Service
public class UserServiceImplement implements UserServiceInterface {
    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ModelMapper mergerMapper;




/*se realiza primero la consulta al repositorio userJpaRepository para buscar un UserEntity basado en el nombre de usuario y
la contraseña proporcionados.Luego, se verifica si se encontró un UserEntity válido (usando userEntity != null).
En caso afirmativo,se establece la fecha actual como el lastLogin del objeto user y se utiliza el modelMapper
para mapear el UserEntity encontrado a un objeto User, que luego se devuelve.Si no se encuentra un UserEntity
correspondiente a las credenciales proporcionadas, se devuelve null.*/
    @Override
    public User authenticateUser(User user) {
        UserEntity userEntity = userJpaRepository.findByUserNameAndPassword(user.getUsername(),user.getPassword());
        if(userEntity != null){
            if (userEntity.isActive()==true)
                user.setLastLogin(LocalDateTime.now());

            return modelMapper.map(userEntity,User.class);
        }
        else
            return null;
    }

    @Override
    public boolean noRegisteredUser(User user) {
        UserEntity userFind = userJpaRepository.findByUserName(user.getUsername());
        return userFind == null;
    }


    /*Se utiliza el ModelMapper para mapear el objeto User recibido al tipo UserEntity.
    Esto convierte el objeto User en una entidad persistente que se puede guardar en la base de datos.
    Se establece el estado activo del usuario en "Y". Esto puede ser un indicador de que el usuario está
    activo dentro del sistema.Se establece la fecha y hora de la última actualización del usuario utilizando
    LocalDateTime.now(). Esto captura la fecha y hora actuales en el momento de la creación del usuario.
    Se guarda el UserEntity en la base de datos utilizando el método save del repositorio userJpaRepository.
    Esto persiste el usuario en la base de datos y devuelve el UserEntity guardado.
    Se utiliza el ModelMapper para mapear el UserEntity guardado de vuelta a un objeto User. Esto convierte
    el UserEntity guardado en una representación de objeto User. Se devuelve el objeto User mapeado,
    que representa al usuario creado y guardado en la base de datos.*/
    @Override
    public User createUser(User user) {
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        userEntity.setLastLogin(LocalDateTime.now());
        userEntity.setActive(true);
        UserEntity savedUserEntity = userJpaRepository.save(userEntity);
        User savedUser = modelMapper.map(savedUserEntity,User.class);

        return savedUser;

        //En una linea
        //return modelMapper.map(userJpaRepository.save(userEntity), User.class);
    }


    @Override
    public User updateUser(User userModificado, Long id) {
        Optional<UserEntity> optionalUserEntity = userJpaRepository.findById(id);
        if(optionalUserEntity.isPresent()){
            UserEntity userEntityAmodificar = optionalUserEntity.get();
            userEntityAmodificar.setLastLogin(LocalDateTime.now());
            userEntityAmodificar.setUserName(userModificado.getUsername());
            userEntityAmodificar.setPassword(userModificado.getPassword());

            // Guardar el UserEntity actualizado en la base de datos utilizando el repositorio
            UserEntity UserEntityModificado = userJpaRepository.save(userEntityAmodificar);

            // Mapear el UserEntity actualizado de vuelta a un objeto User utilizando el ModelMapper
            //User updatedUser = modelMapper.map(updatedUserEntity, User.class);

            return mergerMapper.map(UserEntityModificado,User.class);
        }
        return null;
    }




    @Override
    public List<User> getAllUsers() {
        // Crear una lista vacía para almacenar los objetos User
        List<User> users = new ArrayList<>();

        // Obtener todos los UserEntity de la base de datos utilizando el repositorio
        List<UserEntity> userEntities = userJpaRepository.findAllByIsActive(true);

        // Iterar sobre la lista de UserEntity
        userEntities.forEach(user -> {
            // Utilizar el ModelMapper para mapear cada UserEntity a un objeto User y agregarlo a la lista de users
            users.add(modelMapper.map(user, User.class));
        });

        // Devolver la lista de usuarios mapeados
        return users;
    }

    @Override
    public User findById(Long id) {
        Optional<UserEntity> optionalUserEntity = userJpaRepository.findById(id);
        if(optionalUserEntity.isPresent()){
            UserEntity userEntity = optionalUserEntity.get();
            if(userEntity.isActive()){
                return modelMapper.map(userEntity,User.class);
            }
        }
        return null;
        //hecho antes del isActive:
        // Si el Optional contiene un valor, se realiza el mapeo y se devuelve el objeto User resultante
        // Si el Optional está vacío, se devuelve null
        //return userEntity.map(value -> modelMapper.map(userEntity, User.class)).orElse(null);
    }

    @Override
    public boolean deleteUser(Long id) {
        Optional <UserEntity> optionalUserEntity = userJpaRepository.findById(id);
        if (optionalUserEntity.isPresent()){
            UserEntity userEntity = optionalUserEntity.get();
            userEntity.setActive(false);
            userEntity.setLastLogin(LocalDateTime.now());
            userJpaRepository.save(userEntity);
            return true;
        }
        return false;
    }

}
