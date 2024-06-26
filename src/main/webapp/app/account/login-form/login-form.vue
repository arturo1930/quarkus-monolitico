<template>
  <div class="modal-body">
    <div class="row justify-content-center">
      <div class="col-md-8">
        <b-alert show data-cy="loginError" variant="danger" v-if="authenticationError"
          ><strong>¡El inicio de sesión ha fallado!</strong> Por favor, revise las credenciales e intente de nuevo.</b-alert
        >
      </div>
      <div class="col-md-8">
        <b-form role="form" v-on:submit.prevent="doLogin()">
          <b-form-group label="Usuario" label-for="username">
            <b-form-input
              id="username"
              type="text"
              name="username"
              autofocus
              placeholder="Nombre de usuario"
              v-model="login"
              data-cy="username"
            >
            </b-form-input>
          </b-form-group>
          <b-form-group label="Contraseña" label-for="password">
            <b-form-input id="password" type="password" name="password" placeholder="Su contraseña" v-model="password" data-cy="password">
            </b-form-input>
          </b-form-group>
          <b-form-checkbox id="rememberMe" name="rememberMe" v-model="rememberMe" checked>
            <span>Iniciar la sesión automáticamente</span>
          </b-form-checkbox>
          <div>
            <b-button data-cy="submit" type="submit" variant="primary">Iniciar sesión</b-button>
          </div>
        </b-form>
        <p></p>
        <div>
          <b-alert show variant="warning">
            <b-link :to="'/account/reset/request'" class="alert-link" data-cy="forgetYourPasswordSelector"
              >¿Ha olvidado su contraseña?</b-link
            >
          </b-alert>
        </div>
        <div>
          <b-alert show variant="warning">
            <span>¿Aún no tienes una cuenta?</span>
            <b-link :to="'/register'" class="alert-link">Crea una cuenta</b-link>
          </b-alert>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./login-form.component.ts"></script>
