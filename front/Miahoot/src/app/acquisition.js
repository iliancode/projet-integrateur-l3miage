import Chart from 'chart.js/auto'

(async function() {
  const data = [
    { reponse: 'a', count: 10 },
    { year: 'b', count: 20 },
    { year: 'c', count: 15 },
    { year: 'd', count: 25 },

  ];

  new Chart(
    document.getElementById('acquisitions'),
    {
      type: 'bar',
      data: {
        labels: data.map(row => row.year),
        datasets: [
          {
            label: 'Reponse à la question x',
            data: data.map(row => row.count)
          }
        ]
      }
    }
  );
})();
