import './App.css';
import { Spinner,Button, Form, Col } from "react-bootstrap";
import { Component } from 'react';
import {PositionsComponent} from './PositionsComponent'

class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      countries: [],
      leagues: [],
      teams: [],
      positions: [],
      selectedCountry: null,
      selectedTeam: null,
      selectedLeague: null
    }
  }



  componentDidMount() {
    console.log("component mounted")
    this.getCountries();
  }

  getCountries = () => {
    fetch('/api/countries')
      .then(response => response.json())
      .then(countries => {
        this.setState({ countries: countries });
      })
      .catch(error => {
        console.log("error")
      });
  }

  getLeagues = (countryId) => {
    fetch('/api/leagues/?country=' + countryId)
      .then(response => response.json())
      .then(leagues => {
        this.setState({ leagues: leagues });
      })
      .catch(error => {
        console.log("error")
      });
  }

  getTeams = (leagueId, countryId) => {
    fetch('/api/teams?country=' + countryId + '&league=' + leagueId)
      .then(response => response.json())
      .then(teams => {
        this.setState({ teams: teams });
      })
      .catch(error => {
        console.log("error")
      });
  }

  getPositions = (countryId, leagueId, teamId) => {
    fetch('/api/positions?league=' + leagueId)
      .then(response => response.json())
      .then(positions => {
        this.setState({ 
          positions: positions,
          showModal: true
        });
      })
      .catch(error => {
        console.log("error")
      });
  }

  handleCountrySelection = (event) => {
    console.log(event.target.value)
    this.setState({
      selectedCountry: event.target.value,
      selectedTeam: null,
      selectedLeague: null,
      leagues: [],
      teams: [],
    });
    this.getLeagues(event.target.value)
  }

  handleLeagueSelection = (event) => {
    console.log(event.target.value)
    this.setState({
      selectedTeam: null,
      selectedLeague: event.target.value,
      teams: [],
    });
    this.getTeams(event.target.value, this.state.selectedCountry)
  }

  handleTeamSelection = (event) => {
    console.log(event.target.value)
    this.setState({ selectedTeam: event.target.value });
  }

  handleSubmit = (event) => {
    event.preventDefault();
    this.getPositions(this.state.selectedCountry, this.state.selectedLeague, this.state.selectedTeam)
  }

  handleModalHide = () => {
    this.setState({
      showModal:false
    })
  }


  render() {
    return (
      <div className="App">
        <header className="App-header">
          <h1 className="App-title"> FootBall Standings</h1>
        </header>
        <p />
        <Form onSubmit={this.handleSubmit}>
          <Form.Row>
            <Col md={4}>
              <Form.Group controlId="formGridCountry">
                <Form.Label>Country </Form.Label>
                <Form.Control as="select" defaultValue={null} onChange={this.handleCountrySelection}>
                  <option selected disabled> Select a country...</option>
                  {this.state.countries.map(x => {
                    return <option key={x.country_id} id={x.country_id} value={x.country_id}>{x.country_name}</option>
                  })}
                </Form.Control>
              </Form.Group>
            </Col>

            <Col md={4}>
              <Form.Group controlId="formGridLeage">
                <Form.Label>League</Form.Label>
                <Form.Control as="select" defaultValue={null} disabled={this.state.selectedCountry ? false : true} onChange={this.handleLeagueSelection}>
                  <option selected disabled>  Select a league...</option>
                  {this.state.leagues.map(x => {
                    return <option key={x.league_id} id={x.league_id} value={x.league_id}>{x.league_name}</option>
                  })}
                </Form.Control>
              </Form.Group>
            </Col>

            <Col md={4}>
              <Form.Group controlId="formGridTeam">
                <Form.Label>Team</Form.Label>
                <Form.Control as="select" defaultValue={null} disabled={this.state.selectedCountry && this.state.selectedLeague ? false : true}
                  onChange={this.handleTeamSelection}
                  >
                  <option selected disabled> Select a team...</option>
                  {this.state.teams.map(x => {
                    return <option key={x.team_id} id={x.team_key} value={x.team_key}>{x.team_name}</option>
                  })}
                </Form.Control>
              </Form.Group></Col>
          </Form.Row>
          <Button variant="primary" type="submit" disabled={this.state.selectedCountry && this.state.selectedLeague && this.state.selectedTeam ? false : true}>
            Submit
          </Button>
        </Form>
        <p/>
        {this.state.showModal?this.state.positions.filter(p => p.team_id === this.state.selectedTeam).map(p => {
          return <PositionsComponent position = {p} handleModalHide = {this.handleModalHide}/>
        }):''}
      </div>
    );
  }
}

export default App;
