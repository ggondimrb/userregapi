import React, { useState } from "react";
import ReactDOM from "react-dom";
import useForm from "react-hook-form";
import * as yup from "yup";
import api from "./api";
import "./styles.css";

const SignupSchema = yup.object().shape({
  name: yup.string().required("O nome é obrigatório"),
  email: yup.string().email("Informe um e-mail válido"),
  genre: yup.string(),
  dateBirth: yup.date().required("A data de nascimento é obrigatória")
});

function App() {
  const { register, errors, handleSubmit, reset } = useForm({
    validationSchema: SignupSchema
  });
  const [search, setSearch] = useState("");
  const [currentUser, setCurrentUser] = useState("");

  const onSubmit = async data => {
    let response;
    if (currentUser) {
      try {
        response = await api.put(`user/${currentUser}`, data); 
      } catch (err) {
        console.log(err.response);
        alert(err.response.data.message);
      }      
      
    } else {
      try {
        response = await api.post("user", data);  
      } catch (err) {
        if(err.response.data.errors) {
          alert(err.response.data.errors[0].defaultMessage); 
        } else {
          alert(err.response.data.message);
        }
        
               
      }
      
    }
    console.log("post/put", response);
    if(response){
      setCurrentUser(response.data.id);
    }    
  };

  async function handleSearch() {
    console.log(search);
    let response;
    try {
      response = await api.get(`user/${search}`);
    } catch (err) {
      console.log(err.response);
      alert(err.response.data.message);
    }
    if (response) {
      console.log("search", response);
      reset({ ...response.data });
      setCurrentUser(response.data.id);
    }
  }

  async function handleDelete() {
    let response;
    try{
      response = await api.delete(`user/${search}`);
    } catch(err) {
      alert(err.response.data.message);
    }
    
    console.log("delete", response);
    handleClear();
  }

  function handleClear() {
    reset();
    setCurrentUser("");    
  }

  return (
    <div className="content">
      <div className="content-header">
        <div>
          <label>Buscar usuário</label>
          <input
            type="text"
            name="searchUser"
            style={{ width: "100%" }}
            value={search}
            onChange={e => {
              setSearch(e.target.value);
            }}
          />
        </div>
        <button type="button" onClick={handleSearch}>
          Buscar
        </button>
        <button type="button" onClick={handleClear}>
          Limpar
        </button>
        <button type="button" onClick={handleDelete}>
          Deletar
        </button>
      </div>
      <form onSubmit={handleSubmit(onSubmit)}>
        <div>
          <label>Nome</label>
          <input type="text" name="name" ref={register} />
        </div>
        {errors.name && <p>{errors.name.message}</p>}
        <div>
          <label>Email</label>
          <input type="text" name="email" ref={register} />
        </div>
        {errors.email && <p>{errors.email.message}</p>}
        <div>
          <label>CPF</label>
          <input
            type="text"
            name="cpf"
            ref={register({
              pattern: /^\d{3}\.\d{3}\.\d{3}\-\d{2}$/
            })}
          />
        </div>
        {errors.cpf && <p>{errors.cpf.message}</p>}
        {errors.phone && <p>{errors.phone.message}</p>}
        <div>
          <label>Data de Nascimento</label>
          <input
            type="date"
            name="dateBirth"
            ref={register({ required: true })}
          />
        </div>
        {errors.dateBirth && <p>{errors.dateBirth.message}</p>}
        <div>
          <label>Naturalidade</label>
          <input type="text" name="naturalness" ref={register} />
        </div>
        {errors.naturalness && <p>{errors.naturalness.message}</p>}
        <div>
          <label>Nacionalidade</label>
          <input type="text" name="nationality" ref={register} />
        </div>
        {errors.nationality && <p>{errors.nationality.message}</p>}
        <div>
          <label>Gênero</label>
          <select name="genre" ref={register} style={{ fontSize: "18px" }}>
            <option value="MALE">Masculino</option>
            <option value="FEMALE">Feminino</option>
            <option value="UNDEFINED">Indefinido</option>
          </select>
        </div>
        {errors.genre && <p>{errors.genre.message}</p>}
        <div>
          <label>Endereço</label>
          <input type="text" name="address" ref={register} />
        </div>

        <input type="submit" />
      </form>
    </div>
  );
}

const rootElement = document.getElementById("root");
ReactDOM.render(<App />, rootElement);
