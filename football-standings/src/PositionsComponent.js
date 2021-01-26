import React, { Component } from "react";
import { Button, Modal } from "react-bootstrap";
export class PositionsComponent extends Component {

    constructor(props) {
        console.log(props)
        super(props);
        this.state = {
            country_name: props.position.country_name,
            league_id: props.position.league_id,
            league_name: props.position.league_name,
            team_id: props.position.team_id,
            team_name: props.position.team_name,
            overall_league_position: props.position.overall_league_position,

            //
            show: true

        }
    }

    handleClose = (event) => {
        this.props.handleModalHide()
    }

    render() {
        return (
            <Modal.Dialog show={this.state.show} >
                <Modal.Header>
                    <Modal.Title>Positions</Modal.Title>
                </Modal.Header>

                <Modal.Body>
                    <p>Country Name: {this.state.country_name}</p>
                    <p>League Id: {this.state.league_id}</p>
                    <p>League Name: {this.state.league_name}</p>
                    <p>Team Id: {this.state.team_id}</p>
                    <p>Team Name: {this.state.team_name}</p>
                    <p>Overall League Position: {this.state.overall_league_position}</p>
                </Modal.Body>

                <Modal.Footer>
                    <Button variant="secondary" onClick={this.handleClose}>Close</Button>
                </Modal.Footer>
            </Modal.Dialog>
        )
    }
}
export default PositionsComponent;