
function loadChart(id) {

$.getJSON('http://localhost:12020/api/nse/symbol/'+id, function (data) {
	
  // split the data set into ohlc and volume
  var ohlc = [],
    volume = [],
    downs = [],
    ups = [],
    downFlags = [],
    upFlags = [],
    dataLength = data.length,
    i = 0,
    j = 0,
    k = 0;

  for (i; i < dataLength; i += 1) {
    ohlc.push([
    	data[i]["date"], // the date
        data[i]["open"], // open
        data[i]["high"], // high
        data[i]["low"], // low
        data[i]["close"], // close
    //	data[i]["down"], //down
    //	data[i]["up"] //up
    ]);

    volume.push([
    	 data[i]["date"], // the date
         data[i]["volume"] // the volume
    ]);
    
    downs.push([
    	data[i]["down"] //find the down signals
    ])
    
     ups.push([
    	data[i]["up"] //find the up signals
    ])
    
  }
  
  for(j; j<downs.length; j += 1) {
	  var signal = downs[j];
	  if (signal != 0) {
		  downFlags.push([signal])
	  }
  }
  
  for(k; k<ups.length; k += 1) {
	  var signal = ups[k];
	  if (signal != 0) {
		  upFlags.push([signal])
	  }
  }
  
  
  Highcharts.stockChart('container', {
	
	 yAxis: [{
      labels: {
        align: 'left'
      },
      height: '80%',
      resize: {
        enabled: true
      }
    }, {
      labels: {
        align: 'left'
      },
      top: '80%',
      height: '20%',
      offset: 20
    }],
    tooltip: {
      shape: 'square',
      headerShape: 'callout',
      borderWidth: 0,
      shadow: false,
      positioner: function (width, height, point) {
        var chart = this.chart,
          position;

        if (point.isHeader) {
          position = {
            x: Math.max(
              // Left side limit
              chart.plotLeft,
              Math.min(
                point.plotX + chart.plotLeft - width / 2,
                // Right side limit
                chart.chartWidth - width - chart.marginRight
              )
            ),
            y: point.plotY
          };
        } else {
          position = {
            x: point.series.chart.plotLeft,
            y: point.series.yAxis.top - chart.plotTop
          };
        }

        return position;
      }
    },
    plotOptions: {
        ohlc: {
            color: 'red',
            upColor: 'green',
            lineWidth: 2
         }, 
         candlestick : {
        	 color: 'red',
             upColor: 'green',
             lineWidth: 1
         }
    },
    series: [{
      type: 'ohlc',
      id: id+'-ohlc',
      name: id,
      data: ohlc
    }, 
    {
      type: 'column',
      id: id+'-volume',
      name: id+' Volume',
      data: volume,
      yAxis: 1
    },
    {
        type: 'flags',
        data: [{
            x: downFlags[0],
            title: 'S',
            text: 'Stock is going down',
        },
        {
            x: downFlags[1],
            title: 'S',
            text: 'Stock is going down',
        }, 
        {
            x: downFlags[2],
            title: 'S',
            text: 'Stock is going down',
        }, 
        {
            x: downFlags[3],
            title: 'S',
            text: 'Stock is going down',
        },
        {
            x: downFlags[4],
            title: 'S',
            text: 'Stock is going down',
        },
        {
            x: downFlags[5],
            title: 'S',
            text: 'Stock is going down',
        }, 
        {
            x: downFlags[6],
            title: 'S',
            text: 'Stock is going down',
        }, {
            x: downFlags[7],
            title: 'S',
            text: 'Stock is going down',
        },
        {
            x: downFlags[8],
            title: 'S',
            text: 'Stock is going down',
        },
        {
            x: downFlags[9],
            title: 'S',
            text: 'Stock is going down',
        }, 
        {
            x: downFlags[9],
            title: 'S',
            text: 'Stock is going down',
        }, {
            x: downFlags[10],
            title: 'S',
            text: 'Stock is going down',
        },
        {
            x: downFlags[11],
            title: 'S',
            text: 'Stock is going down',
        },
        {
            x: downFlags[12],
            title: 'S',
            text: 'Stock is going down',
        }, 
        {
            x: downFlags[13],
            title: 'S',
            text: 'Stock is going down',
        }, {
            x: downFlags[14],
            title: 'S',
            text: 'Stock is going down',
        },
        {
            x: downFlags[15],
            title: 'S',
            text: 'Stock is going down',
        },
        {
            x: downFlags[16],
            title: 'S',
            text: 'Stock is going down',
        }, 
        {
            x: downFlags[17],
            title: 'S',
            text: 'Stock is going down',
        }, {
            x: downFlags[18],
            title: 'S',
            text: 'Stock is going down',
        },
        {
            x: downFlags[19],
            title: 'S',
            text: 'Stock is going down',
        },
        {
            x: downFlags[20],
            title: 'S',
            text: 'Stock is going down',
        }, 
        {
            x: downFlags[21],
            title: 'S',
            text: 'Stock is going down',
        }, {
            x: downFlags[22],
            title: 'S',
            text: 'Stock is going down',
        },
        {
            x: downFlags[23],
            title: 'S',
            text: 'Stock is going down',
        },
        {
            x: downFlags[24],
            title: 'S',
            text: 'Stock is going down',
        }, 
        {
            x: downFlags[25],
            title: 'S',
            text: 'Stock is going down',
        }, {
            x: downFlags[26],
            title: 'S',
            text: 'Stock is going down',
        },
        {
            x: downFlags[27],
            title: 'S',
            text: 'Stock is going down',
        },
        {
            x: downFlags[28],
            title: 'S',
            text: 'Stock is going down',
        }, 
        {
            x: downFlags[29],
            title: 'S',
            text: 'Stock is going down',
        }, {
            x: downFlags[30],
            title: 'S',
            text: 'Stock is going down',
        },
        {
            x: downFlags[31],
            title: 'S',
            text: 'Stock is going down',
        },
        {
            x: downFlags[32],
            title: 'S',
            text: 'Stock is going down',
        }, 
        {
            x: downFlags[33],
            title: 'S',
            text: 'Stock is going down',
        }, {
            x: downFlags[34],
            title: 'S',
            text: 'Stock is going down',
        },
        {
            x: downFlags[35],
            title: 'S',
            text: 'Stock is going down',
        },
        {
            x: downFlags[36],
            title: 'S',
            text: 'Stock is going down',
        }, 
        {
            x: downFlags[37],
            title: 'S',
            text: 'Stock is going down',
        }, {
            x: downFlags[38],
            title: 'S',
            text: 'Stock is going down',
        },
        {
            x: downFlags[39],
            title: 'S',
            text: 'Stock is going down',
        },
        {
            x: downFlags[40],
            title: 'S',
            text: 'Stock is going down',
        }, 
        {
            x: downFlags[41],
            title: 'S',
            text: 'Stock is going down',
        }, {
            x: downFlags[41],
            title: 'S',
            text: 'Stock is going down',
        },
        {
            x: downFlags[42],
            title: 'S',
            text: 'Stock is going down',
        },
        {
            x: downFlags[43],
            title: 'S',
            text: 'Stock is going down',
        }, 
        {
            x: downFlags[44],
            title: 'S',
            text: 'Stock is going down',
        }, {
            x: downFlags[45],
            title: 'S',
            text: 'Stock is going down',
        },
        {
            x: downFlags[46],
            title: 'S',
            text: 'Stock is going down',
        },
        {
            x: downFlags[47],
            title: 'S',
            text: 'Stock is going down',
        }, 
        {
            x: downFlags[48],
            title: 'S',
            text: 'Stock is going down',
        }, {
            x: downFlags[49],
            title: 'S',
            text: 'Stock is going down',
        },
        {
            x: downFlags[50],
            title: 'S',
            text: 'Stock is going down',
        },
        {
            x: upFlags[0],
            title: 'B',
            text: 'Stock is going up',
        },
        {
            x: upFlags[1],
            title: 'B',
            text: 'Stock is going up',
        }, 
        {
            x: upFlags[2],
            title: 'B',
            text: 'Stock is going up',
        }, {
            x: upFlags[3],
            title: 'B',
            text: 'Stock is going up',
        },
        {
            x: upFlags[4],
            title: 'B',
            text: 'Stock is going up',
        },
        {
            x: upFlags[5],
            title: 'B',
            text: 'Stock is going up',
        }, 
        {
            x: upFlags[6],
            title: 'B',
            text: 'Stock is going up',
        }, {
            x: upFlags[7],
            title: 'B',
            text: 'Stock is going up',
        },
        {
            x: upFlags[8],
            title: 'B',
            text: 'Stock is going up',
        },
        {
            x: upFlags[9],
            title: 'B',
            text: 'Stock is going up',
        }, 
        {
            x: upFlags[9],
            title: 'B',
            text: 'Stock is going up',
        }, {
            x: upFlags[10],
            title: 'B',
            text: 'Stock is going up',
        },
        {
            x: upFlags[11],
            title: 'B',
            text: 'Stock is going up',
        },
        {
            x: upFlags[12],
            title: 'B',
            text: 'Stock is going up',
        }, 
        {
            x: upFlags[13],
            title: 'B',
            text: 'Stock is going up',
        }, {
            x: upFlags[14],
            title: 'B',
            text: 'Stock is going up',
        },
        {
            x: upFlags[15],
            title: 'B',
            text: 'Stock is going up',
        },
        {
            x: upFlags[16],
            title: 'B',
            text: 'Stock is going up',
        }, 
        {
            x: upFlags[17],
            title: 'B',
            text: 'Stock is going up',
        }, {
            x: upFlags[18],
            title: 'B',
            text: 'Stock is going up',
        },
        {
            x: upFlags[19],
            title: 'B',
            text: 'Stock is going up',
        },
        {
            x: upFlags[20],
            title: 'B',
            text: 'Stock is going up',
        }, 
        {
            x: upFlags[21],
            title: 'B',
            text: 'Stock is going up',
        }, {
            x: upFlags[22],
            title: 'B',
            text: 'Stock is going up',
        },
        {
            x: upFlags[23],
            title: 'B',
            text: 'Stock is going up',
        },
        {
            x: upFlags[24],
            title: 'B',
            text: 'Stock is going up',
        }, 
        {
            x: upFlags[25],
            title: 'B',
            text: 'Stock is going up',
        }, {
            x: upFlags[26],
            title: 'B',
            text: 'Stock is going up',
        },
        {
            x: upFlags[27],
            title: 'B',
            text: 'Stock is going up',
        },
        {
            x: upFlags[28],
            title: 'B',
            text: 'Stock is going up',
        }, 
        {
            x: upFlags[29],
            title: 'B',
            text: 'Stock is going up',
        }, {
            x: upFlags[30],
            title: 'B',
            text: 'Stock is going up',
        },
        {
            x: upFlags[31],
            title: 'B',
            text: 'Stock is going up',
        },
        {
            x: upFlags[32],
            title: 'B',
            text: 'Stock is going up',
        }, 
        {
            x: upFlags[33],
            title: 'B',
            text: 'Stock is going up',
        }, {
            x: upFlags[34],
            title: 'B',
            text: 'Stock is going up',
        },
        {
            x: upFlags[35],
            title: 'B',
            text: 'Stock is going up',
        },
        {
            x: upFlags[36],
            title: 'B',
            text: 'Stock is going up',
        }, 
        {
            x: upFlags[37],
            title: 'B',
            text: 'Stock is going up',
        }, {
            x: upFlags[38],
            title: 'B',
            text: 'Stock is going up',
        },
        {
            x: upFlags[39],
            title: 'B',
            text: 'Stock is going up',
        },
        {
            x: upFlags[40],
            title: 'B',
            text: 'Stock is going up',
        }, 
        {
            x: upFlags[41],
            title: 'B',
            text: 'Stock is going up',
        }, {
            x: upFlags[41],
            title: 'B',
            text: 'Stock is going up',
        },
        {
            x: upFlags[42],
            title: 'B',
            text: 'Stock is going up',
        },
        {
            x: upFlags[43],
            title: 'B',
            text: 'Stock is going up',
        }, 
        {
            x: upFlags[44],
            title: 'B',
            text: 'Stock is going up',
        }, {
            x: upFlags[45],
            title: 'B',
            text: 'Stock is going up',
        },
        {
            x: upFlags[46],
            title: 'B',
            text: 'Stock is going up',
        },
        {
            x: upFlags[47],
            title: 'B',
            text: 'Stock is going up',
        }, 
        {
            x: upFlags[48],
            title: 'B',
            text: 'Stock is going up',
        }, {
            x: upFlags[49],
            title: 'B',
            text: 'Stock is going up',
        },
        {
            x: upFlags[50],
            title: 'B',
            text: 'Stock is going up',
        }
        ],
        onSeries: id+'-ohlc',
        shape: 'square',
        width: 30
    }, 
    ],
    responsive: {
      rules: [{
        condition: {
          maxWidth: 800
        },
        chartOptions: {
          rangeSelector: {
        	inputEnabled: true
          }
        }
      }]
    }
  });
});
}