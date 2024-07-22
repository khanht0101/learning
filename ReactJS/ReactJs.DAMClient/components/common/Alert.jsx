import React from 'react';
import PropTypes from 'prop-types';
import Modal from 'react-bootstrap/Modal';
import Spinner from './Spinner';
import Info from './Info';

const ResourceAlert = ({
    showModal,
    backDropStatic,
    onHide,
    message,
}) => {

    const renderIcon = () => {
        switch (message.status) {
            case 'loading':
                return <Spinner />
            case 'failed':
                return null
            case 'info':
                    return <Info />
            default:
                return <Spinner />
        }
    }
    return (
        <Modal
            show={showModal}
            centered
            backdrop={backDropStatic ? 'static' : true}
            onHide={onHide}
            contentClassName={'rounded-0'}
            keyboard={false}
        >
            <Modal.Body className="text-center">
                {renderIcon()}
                {message.message}
            </Modal.Body>
        </Modal>
    );
};

ResourceAlert.propTypes = {
    showModal: PropTypes.bool,
    backDropStatic: PropTypes.bool,
    onHide: PropTypes.func,
    message: PropTypes.object,
}

export default ResourceAlert;
