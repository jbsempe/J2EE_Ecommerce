# Barber

Barber is a tiny JavaScript skeleton. It's so small, that it can't even be called a framework.

It works with simple plain old JavaScript and standard HTML. You don't need to setup any pipeline. You just copy Barber source code in your project and it works.

Here is a simple hello world example to give you an idea :

```html
<div data-barber="HelloWorld">
  <input type="text" data-role="nameInput"/>
  <button data-event="click->sayHi">Say hi</button>
  <p data-role="hiLabel"></p>
</div>
```

```js
HelloWorld = function(container) {
}

HelloWorld.prototype.sayHi = function(event) {
  this.hiLabel.textContent = "Hello " + this.nameInput.value
}
```

## Installation

1. Copy barber.js in your project
2. Call `Barber.launchWhenDomIsReady()`
3. Done


## Documentation

To master Barber you need to understand only 4 things : controllers, events, roles and Mustache templates.

### Controller

A controller is a JavaScript function connected to a DOM element. Barber looks for all the `data-barber="ControllerName"` attributes and finds the relevant controller.
It handles nested names such as `data-barber="Account.Form"`. In that case a new object `Account.Form` is created and receives the DOM element as an argument.

The controller is automatically initialized with events and roles that we will discuss in next sections. Moreover you can access to the controller element directly with `this.container` from the controller.

```html
<div data-barber="HelloWorld"></div>
```

```js
HelloWorld = function(container) {
  // container is the div element but it has also been assigned by Barber to
  // this.container to let you use it in other functions.
  this.container == container // => true
}
```

### Events

Barber helps you to listen to DOM events :

```html
<span data-event="click->saveForm">Button</span>
```

When the span is clicked, the function saveForm of the controller is called. It also possible to define many events at the same time :

```html
<span data-event="click->saveForm mouseover->highlightButton">Button</span>
```

### Roles

Roles help you to access easily to DOM elements from your controller with a nice name instead of a CSS selector.

```html
<form data-barber="Form">
  <input type="text" data-role="textFields"/>
  <input type="text" data-role="textFields"/>
  <span data-role="saveButton">Button</span>
</form>
```

```js
Form = function(container) {
  this.saveButton // => <span></span>
  this.roles.saveButton // => [<span></span>]
  this.roles.textFields // => [<input/> <input/>]
}
```

All roles are stored in arrays into `this.roles.roleName`. However if there is only 1 occurence of the role, there is the following shortcut to access it : `this.roleName`. In the previous example that is the case for saveButton but not for textFields.

### Mustache templates

Mustache is needed for client side template rendering only. You can use Barber without Mustache. However the joke of the name comes of course from Mustache ( ͡° ͜ʖ ͡°).

This part of Barber is useful when your application needs more interaction on the client side. If you are not familiar with Mustache you can learn it very easily https://mustache.github.io. Then copy mustache.js (https://github.com/janl/mustache.js/blob/master/mustache.js) into your project.

```html
<div data-barber="List"></div>

<script type="x-tmpl-mustache" data-partial="RorVsWild.Local">
  <ul>
    {#items}
      <li>{{name}}</li>
    {/items}
  </ul>
</script>
```

TODO Faire des examples :
  1. Hello world
  2. Sélection de liste avec Barber


## Compare to Stimulus

You will notice that there are a lot of similarities with Stimulus. Indeed, they both share the same ideas of simplicity and reusing HTML markup.
But, Barber has also the ambition to also let you reuse your JavaScript code thanks to a very light convention.

Even if Barber has been released a long time after Stimulus it has been used in many project such hash RorVsWild (https://github.com/BaseSecrete/rorvswild) (for the local profiling) before Stimulus release.
