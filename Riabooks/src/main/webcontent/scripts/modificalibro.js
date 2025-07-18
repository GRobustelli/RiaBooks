/**
 * 
 */

function sanitizeForm(formId) {
  const form = document.getElementById(formId);
  if (!form) return;
  
  console.log("Stiamo dentro a sanitize");
  const encodeHTML = (str) => {
    return str.replace(/&/g, "&amp;")
              .replace(/</g, "&lt;")
              .replace(/>/g, "&gt;")
              .replace(/"/g, "&quot;")
              .replace(/'/g, "&#39;");
  };

  const fields = form.querySelectorAll("input[type='text'], textarea");

  fields.forEach((field) => {
    field.value = encodeHTML(field.value);
  });
}


