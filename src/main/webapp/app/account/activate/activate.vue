<template>
  <div>
    <div class="row justify-content-center">
      <div class="col-md-8">
        <h1>Activación</h1>
        <div class="alert alert-success" v-if="success">
          <span><strong>Su cuenta ha sido activada.</strong> Por favor, </span>
          <a class="alert-link" v-on:click="openLogin">iniciar sesión</a>.
        </div>
        <div class="alert alert-danger" v-if="error">
          <strong>Su cuenta no pudo ser activada.</strong> Por favor, utilice el formulario de inscripción para registrarse.
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./activate.component.ts"></script>
