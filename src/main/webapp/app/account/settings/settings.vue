<template>
  <div>
    <div class="row justify-content-center">
      <div class="col-md-8 toastify-container">
        <h2 v-if="username" id="settings-title">
          <span
            >Ajustes del usuario [<strong>{{ username }}</strong
            >]</span
          >
        </h2>

        <div class="alert alert-success" role="alert" v-if="success"><strong>¡Ajustes guardados!</strong></div>

        <div class="alert alert-danger" role="alert" v-if="errorEmailExists">
          <strong>¡El correo electrónico ya está en uso!</strong> Por favor, escoja otro email.
        </div>

        <form name="form" id="settings-form" role="form" v-on:submit.prevent="save()" v-if="settingsAccount" novalidate>
          <div class="form-group">
            <label class="form-control-label" for="firstName">Nombre</label>
            <input
              type="text"
              class="form-control"
              id="firstName"
              name="firstName"
              placeholder="Su nombre"
              :class="{ valid: !v$.settingsAccount.firstName.$invalid, invalid: v$.settingsAccount.firstName.$invalid }"
              v-model="v$.settingsAccount.firstName.$model"
              minlength="1"
              maxlength="50"
              required
              data-cy="firstname"
            />
            <div v-if="v$.settingsAccount.firstName.$anyDirty && v$.settingsAccount.firstName.$invalid">
              <small class="form-text text-danger" v-if="!v$.settingsAccount.firstName.required">Se requiere que ingrese su nombre.</small>
              <small class="form-text text-danger" v-if="!v$.settingsAccount.firstName.minLength"
                >Se requiere que su nombre tenga por lo menos 1 caracter</small
              >
              <small class="form-text text-danger" v-if="!v$.settingsAccount.firstName.maxLength"
                >Su nombre no puede tener más de 50 caracteres</small
              >
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lastName">Apellidos</label>
            <input
              type="text"
              class="form-control"
              id="lastName"
              name="lastName"
              placeholder="Sus apellidos"
              :class="{ valid: !v$.settingsAccount.lastName.$invalid, invalid: v$.settingsAccount.lastName.$invalid }"
              v-model="v$.settingsAccount.lastName.$model"
              minlength="1"
              maxlength="50"
              required
              data-cy="lastname"
            />
            <div v-if="v$.settingsAccount.lastName.$anyDirty && v$.settingsAccount.lastName.$invalid">
              <small class="form-text text-danger" v-if="!v$.settingsAccount.lastName.required"
                >Se requiere que ingrese sus apellidos.</small
              >
              <small class="form-text text-danger" v-if="!v$.settingsAccount.lastName.minLength"
                >Se requiere que sus apellidos tengan por lo menos 1 caracter</small
              >
              <small class="form-text text-danger" v-if="!v$.settingsAccount.lastName.maxLength"
                >Sus apellidos no pueden tener más de 50 caracteres</small
              >
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="email">Correo electrónico</label>
            <input
              type="email"
              class="form-control"
              id="email"
              name="email"
              placeholder="Su correo electrónico"
              :class="{ valid: !v$.settingsAccount.email.$invalid, invalid: v$.settingsAccount.email.$invalid }"
              v-model="v$.settingsAccount.email.$model"
              minlength="5"
              maxlength="254"
              email
              required
              data-cy="email"
            />
            <div v-if="v$.settingsAccount.email.$anyDirty && v$.settingsAccount.email.$invalid">
              <small class="form-text text-danger" v-if="!v$.settingsAccount.email.required">Se requiere un correo electrónico.</small>
              <small class="form-text text-danger" v-if="!v$.settingsAccount.email.email">Su correo electrónico no es válido.</small>
              <small class="form-text text-danger" v-if="!v$.settingsAccount.email.minLength"
                >Se requiere que su correo electrónico tenga por lo menos 5 caracteres</small
              >
              <small class="form-text text-danger" v-if="!v$.settingsAccount.email.maxLength"
                >Su correo electrónico no puede tener más de 50 caracteres</small
              >
            </div>
          </div>
          <button type="submit" :disabled="v$.settingsAccount.$invalid" class="btn btn-primary" data-cy="submit">Guardar</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./settings.component.ts"></script>
