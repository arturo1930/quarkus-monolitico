<template>
  <div class="home row">
    <div class="col-md-3">
      <span class="hipster img-fluid rounded"></span>
    </div>
    <div class="col-md-9">
      <h1 class="display-4">¡Bienvenido, Java Hipster!</h1>
      <p class="lead">Esta es su página de inicio</p>

      <div>
        <div class="alert alert-success" v-if="authenticated">
          <span v-if="username">Está conectado como "{{ username }}".</span>
        </div>

        <div class="alert alert-warning" v-if="!authenticated">
          <span>Si desea </span>
          <a class="alert-link" v-on:click="openLogin()">iniciar sesión</a
          ><span
            >, puede intentar con las cuentas predeterminadas:<br />- Administrador (usuario="admin" y contraseña="admin") <br />- Usuario
            (usuario="user" y contraseña="user").</span
          >
        </div>
        <div class="alert alert-warning" v-if="!authenticated">
          <span>¿Aún no tienes una cuenta?</span>&nbsp;
          <router-link class="alert-link" to="/register">Crea una cuenta</router-link>
        </div>
      </div>

      <p>Si tiene preguntas sobre JHipster:</p>

      <ul>
        <li><a href="https://www.jhipster.tech/" target="_blank" rel="noopener noreferrer">Página de inicio de JHipster</a></li>
        <li>
          <a href="http://stackoverflow.com/tags/jhipster/info" target="_blank" rel="noopener noreferrer">JHipster en Stack Overflow</a>
        </li>
        <li>
          <a href="https://github.com/jhipster/generator-jhipster/issues?state=open" target="_blank" rel="noopener noreferrer"
            >JHipster seguimiento de errores</a
          >
        </li>
        <li>
          <a href="https://gitter.im/jhipster/generator-jhipster" target="_blank" rel="noopener noreferrer"
            >Sala de chat pública de JHipster</a
          >
        </li>
        <li>
          <a href="https://twitter.com/jhipster" target="_blank" rel="noopener noreferrer">contacto @jhipster en Twitter</a>
        </li>
      </ul>

      <p>
        <span>Si te gusta JHipster, danos una estrella en</span>
        <a href="https://github.com/jhipster/generator-jhipster" target="_blank" rel="noopener noreferrer">GitHub</a>!
      </p>
    </div>
  </div>
</template>

<script lang="ts" src="./home.component.ts"></script>
