new DataTable('#example', {
                    "language": {
                        "emptyTable": "No hay información",
                        "decimal": "",
                        "info": "Mostrando _START_ a _END_ de _TOTAL_ Registros",
                        "infoEmpty": "Mostrando 0 to 0 of 0 Registros",
                        "infoFiltered": "(Filtrado de MAX total Registros)",
                        "infoPostFix": "",
                        "thousands": ",",
                        "lengthMenu": "Mostrar _MENU_ Registros",
                        "loadingRecords": "Cargando...",
                        "processing": "Procesando...",
                        "search": "Buscador:",
                        "zeroRecords": "Sin resultados encontrados",
                        "paginate": {
                            "first": "Primero",
                            "last": "Ultimo",
                            "next": "Siguiente",
                            "previous": "Anterior"
                        }
                       },
                "responsive": true, "lengthChange": true, "autoWidth": false,
                buttons: [{
                          extend: 'collection',
                          text: 'Reportes',
                          orientation: 'landscape',
                          buttons: [{
                              text: 'copiar',
                              extend: 'copy',
                          }, {
                              extend: 'pdf',
                          }, {
                              extend: 'csv',
                          }, {
                              extend: 'excel',
                          }, {
                              text: 'Imprimir',
                              extend: 'print'
                          }
                      ]
                      },
                      {
                          extend: 'colvis',
                          text: 'Vista de Columna',
                          collectionLayout: 'fixed three-column'
                      }
          
                  ],
              }).buttons().container().appendTo('#example_wrapper .col-md-6:eq(0)');




